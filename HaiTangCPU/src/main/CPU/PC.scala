
import chisel3._
import chisel3.util._
import def_pc_sel._
class PC extends Module{
    val io = IO(
        new PC_interface
    )
    val pc_reg = RegInit("h80000000".U(width.pc_width.W))
    // val Jump_addr = MuxLookup(io.pc_sel,"h80000000".U(width.pc_width.W))(Seq(
    //     pc_sel_default -> io.alu_out_data,
    //     pc_sel_alu_out -> io.alu_out_data
    // ))
    val Jump_addr = io.alu_out_data
    pc_reg := Mux(io.if_Jump,Jump_addr,Mux(io.if_valid,pc_reg+4.U,pc_reg))//Mux(io.if_valid,Mux(io.if_Jump,Jump_addr,io.now_pc+4.U),pc_reg)
    io.now_pc := pc_reg
}