import chisel3._
import chisel3.util._
import width._
class GenericRegisterGroup  extends Module{
    val io = IO(new GenericRegisterGroup_interface())

    val reg_group = RegInit(VecInit(Seq.fill(reg_count)(0.U(reg_width.W))))//RegInit(Vec(reg_count,UInt(reg_width.W)))
    io.rj_data := Mux(io.rj_addr=/=0.U,reg_group(io.rj_addr),0.U)
    io.rk_data := Mux(io.rk_addr=/=0.U,reg_group(io.rk_addr),0.U)
    io.rd_data_out := Mux(io.rd_addr_out=/=0.U,reg_group(io.rd_addr_out),0.U)//Mux(io.rd_addr_in===io.rd_addr_out,io.rd_data_in,reg_group(io.rd_addr_out))
    
    when(io.rd_en === true.B && io.rd_addr_in =/= 0.U ){
        reg_group(io.rd_addr_in) := io.rd_data_in
    }
}