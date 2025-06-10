import chisel3._
import chisel3.util._
import width._
import def_alu_option._

class ALU extends RawModule {
  val io = IO(new ALU_interface())
  
  val alu_result_default = 0.U
  val alu_result_mov = io.in_data2 //note
  val alu_result_add = (io.in_data1.asUInt + io.in_data2.asUInt)
  val alu_result_sub = (io.in_data1.asUInt - io.in_data2.asUInt)
  val alu_result_or =  io.in_data1 | io.in_data2
  val alu_result_and =  io.in_data1 & io.in_data2
  val alu_result_xor =  io.in_data1 ^ io.in_data2
  val alu_result_sll = ((io.in_data1.asUInt << io.in_data2.asUInt(4,0)))
  val alu_result_srl = (io.in_data1.asUInt >> io.in_data2.asUInt(4,0))
  val alu_result_mul_tmp = Wire(SInt(64.W))
  alu_result_mul_tmp := io.in_data1.asSInt * io.in_data2.asSInt
  val alu_result_mul = ((io.in_data1.asSInt * io.in_data2.asSInt)(31,0)).asUInt //alu_result_mul_tmp(31,0).asSInt
  val alu_result_SLTI = Mux(io.in_data1.asSInt < io.in_data2.asSInt,1.U(32.W),0.U(32.W))
  val alu_result = MuxLookup(io.alu_option, 0.U(Data_width.W))(
    Seq(
        alu_default -> alu_result_default,
        alu_mov -> alu_result_mov,
        alu_add -> alu_result_add,
        alu_sub -> alu_result_sub,
        alu_or  -> alu_result_or,
        alu_and -> alu_result_and,  
        alu_xor -> alu_result_xor,
        alu_srl -> alu_result_srl,
        alu_sll -> alu_result_sll,
        alu_mul -> alu_result_mul,
        alu_SLTI -> alu_result_SLTI
    )
  )
  io.out_data := alu_result
}
