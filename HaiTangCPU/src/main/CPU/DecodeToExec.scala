import chisel3._
import chisel3.util._
import def_GRGDataSel_option._
class DecodeToExec extends Module{
    val io = IO(new Bundle{
        val in = Flipped(Decoupled(new DecodeToExec_interface()))
        val out = Decoupled(new DecodeToExec_interface())
        val DecodeToExec_Data_Conflict = new DecodeToExec_Data_Conflict_interface()
        val if_var_pc_Conflict = Input(Bool())
    })
    val DecodeToExec_valid = RegInit(false.B)
    //val DecodeToExec_ready = RegInit(true.B)
    val DecodeToExec_go_in = Wire(Bool())
    val s_idle :: ld_data_Conflict ::go:: go2::go3::go4::go5::Nil = Enum(7)
    
    val ld_data_Conflict_state = RegInit(s_idle)
    val if_ld_data_Conflict = Wire(Bool())

    val if_ready_data_Conflict = Mux(ld_data_Conflict_state===go4,true.B,Mux(ld_data_Conflict_state=/=s_idle,false.B,true.B))
    DecodeToExec_valid :=Mux( DecodeToExec_go_in,io.in.valid & io.if_var_pc_Conflict , DecodeToExec_valid)
    DecodeToExec_go_in :=  (io.out.ready | !DecodeToExec_valid) & Mux(ld_data_Conflict_state=/=s_idle,false.B,true.B)//& !if_ready_data_Conflict//
    //DecodeToExec_ready := io.out.ready //& !if_ld_data_Conflict
    io.in.ready := DecodeToExec_go_in 
    io.out.valid := DecodeToExec_valid
    val DecodeToExec_reg_init = Wire(new DecodeToExec_interface())
    //init value
    DecodeToExec_reg_init.alu_option := 0.U
    DecodeToExec_reg_init.MemoryAccess_option :=0.U
    DecodeToExec_reg_init.alu_a_sel :=0.U
    DecodeToExec_reg_init.alu_b_sel := 0.U
    DecodeToExec_reg_init.pc_sel:= 0.U
    DecodeToExec_reg_init.ram_r := false.B
    DecodeToExec_reg_init.ram_w :=false.B
    DecodeToExec_reg_init.Out_Eximm := 0.U
    DecodeToExec_reg_init.rj_data := 0.U
    DecodeToExec_reg_init.rk_data := 0.U
    DecodeToExec_reg_init.rd_data_out := 0.U
    DecodeToExec_reg_init.now_pc := 0.U
    DecodeToExec_reg_init.if_Jump := false.B
    DecodeToExec_reg_init.rd_addr := 0.U
    DecodeToExec_reg_init.rd_en :=false.B
    DecodeToExec_reg_init.GRGDataSel_option := 0.U
    DecodeToExec_reg_init.var_pc_addr := 0.U
    

    val DecodeToExec_reg = RegEnable(io.in.bits,DecodeToExec_reg_init,DecodeToExec_go_in)//,DecodeToExec_reg_init
    io.out.bits := Mux(DecodeToExec_valid,DecodeToExec_reg,DecodeToExec_reg_init)//<> DecodeToExec_reg

    
    //处理应访存指令，而导致的数据冲突 Mux(io.DecodeToExec_Data_Conflict.MemoryAccess_mem_r&(io.DecodeToExec_Data_Conflict.Decode_rj_addr===io.DecodeToExec_Data_Conflict.MemoryAccess_rd_addr | io.in.bits.rd_addr===io.DecodeToExec_Data_Conflict.MemoryAccess_rd_addr | io.DecodeToExec_Data_Conflict.Decode_rk_addr===io.DecodeToExec_Data_Conflict.MemoryAccess_rd_addr ),true.B,false.B)
    if_ld_data_Conflict := Mux(io.in.bits.ram_r| io.in.bits.ram_w,true.B,false.B)
    ld_data_Conflict_state := MuxLookup(ld_data_Conflict_state,s_idle)(
        List(
            s_idle -> Mux(if_ld_data_Conflict,ld_data_Conflict,s_idle),
            ld_data_Conflict -> go,
            go -> go2,
            go2->s_idle,
            go3->s_idle
        )
    )
}

