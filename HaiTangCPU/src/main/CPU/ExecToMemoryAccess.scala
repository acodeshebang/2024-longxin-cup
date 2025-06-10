import chisel3._
import chisel3.util._

class ExecToMemoryAccess extends Module{
    val io = IO(new Bundle{
        val in = Flipped(Decoupled(new ExecToMemoryAccess_interface()))
        val out = Decoupled(new ExecToMemoryAccess_interface())
    })
    val ExecToMemoryAccess_valid = RegInit(false.B)
    //val ExecToMemoryAccess_ready = RegInit(true.B)
    val ExecToMemoryAccess_go_in = Wire(Bool())

    ExecToMemoryAccess_valid:=Mux(ExecToMemoryAccess_go_in,io.in.valid,ExecToMemoryAccess_valid)
    ExecToMemoryAccess_go_in := io.out.ready | !ExecToMemoryAccess_valid
    //ExecToMemoryAccess_ready := io.out.ready

    io.in.ready := ExecToMemoryAccess_go_in
    io.out.valid := ExecToMemoryAccess_valid
    val ExecToMemoryAccess_reg_init = Wire(new ExecToMemoryAccess_interface())
    //init value
    ExecToMemoryAccess_reg_init.MemoryAccess_option := 0.U
    ExecToMemoryAccess_reg_init.alu_out_data := 0.U
    ExecToMemoryAccess_reg_init.pc_sel := 0.U
    ExecToMemoryAccess_reg_init.ram_r :=false.B
    ExecToMemoryAccess_reg_init.ram_w := false.B
    ExecToMemoryAccess_reg_init.GRGDataSel_option := 0.U
    ExecToMemoryAccess_reg_init.rd_data_out := 0.U
    ExecToMemoryAccess_reg_init.if_Jump := false.B
    ExecToMemoryAccess_reg_init.rd_addr := 0.U
    ExecToMemoryAccess_reg_init.rd_en := false.B
    ExecToMemoryAccess_reg_init.now_pc := 0.U


    val ExecToMemoryAccess_reg = RegEnable(io.in.bits,ExecToMemoryAccess_reg_init,ExecToMemoryAccess_go_in)//,ExecToMemoryAccess_reg_init
    io.out.bits := Mux(ExecToMemoryAccess_valid,ExecToMemoryAccess_reg,ExecToMemoryAccess_reg_init)//<> ExecToMemoryAccess_reg
}

    // val MemoryAccess_option = Output(UInt(MemoryAccess_option_width.W))
    // val alu_out_data = Output(UInt(Data_width.W))
    // val pc_sel = Output(UInt(pc_sel_width.W))
    // val ram_r   = Output(Bool())
    // val ram_w   = Output(Bool())
    // val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    // val rd_data_out = Output(UInt(reg_width.W))
    // val if_Jump = Output(Bool())
    // val rd_addr = Output(UInt(reg_addr_width.W))
    // val rd_en = Output(Bool())