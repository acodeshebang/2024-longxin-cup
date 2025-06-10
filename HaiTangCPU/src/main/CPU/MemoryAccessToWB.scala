import chisel3._
import chisel3.util._

class MemoryAccessToWB extends Module{
    val io = IO(new Bundle{
        val in = Flipped(Decoupled(new MemoryAccessToWB_interface()))
        val out = Decoupled(new MemoryAccessToWB_interface())
    })
    val MemoryAccessToWB_valid = RegInit(false.B)
    val MemoryAccessToWB_go_in = Wire(Bool())

    MemoryAccessToWB_valid := Mux(MemoryAccessToWB_go_in,io.in.valid,MemoryAccessToWB_valid)
    MemoryAccessToWB_go_in :=  !MemoryAccessToWB_valid | io.out.ready
    io.in.ready := MemoryAccessToWB_go_in
    io.out.valid := MemoryAccessToWB_valid
    val MemoryAccessToWB_reg_init = Wire(new MemoryAccessToWB_interface())
    //init value
    MemoryAccessToWB_reg_init.GRGDataSel_option := 0.U
    MemoryAccessToWB_reg_init.alu_out_data := 0.U
    MemoryAccessToWB_reg_init.MemoryAccess_out_data := 0.U
    MemoryAccessToWB_reg_init.pc_sel := 0.U
    MemoryAccessToWB_reg_init.if_Jump := false.B
    MemoryAccessToWB_reg_init.rd_addr := 0.U
    MemoryAccessToWB_reg_init.rd_en := false.B
    MemoryAccessToWB_reg_init.now_pc := 0.U

    val MemoryAccessToWB_reg = RegEnable(io.in.bits,MemoryAccessToWB_reg_init,MemoryAccessToWB_go_in)//MemoryAccessToWB_reg_init,
    io.out.bits := Mux(MemoryAccessToWB_valid,MemoryAccessToWB_reg,MemoryAccessToWB_reg_init)//<> MemoryAccessToWB_reg


}

    // val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    // val alu_out_data = Output(UInt(Data_width.W))
    // val MemoryAccess_out_data = Output(UInt(Data_width.W))
    // val pc_sel = Output(UInt(pc_sel_width.W))
    // val if_Jump = Output(Bool())
    // val rd_addr = Output(UInt(reg_addr_width.W))
    // val rd_en = Output(Bool())