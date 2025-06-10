import chisel3._
import chisel3.util._
import def_imm_option._
import width._

class imm extends Module {
  val io                 = IO(new imm_interface())
  val imm_result_default = 0.S(Data_width.W)
  val imm_result_2RI8    = Cat(io.Instruction(17, 10)).asSInt
  val imm_result_2RI12   = Cat(io.Instruction(21, 10)).asSInt
  val imm_result_2RI14   = Cat(io.Instruction(23, 10)).asSInt
  val imm_result_2RI16   = Cat(io.Instruction(25,10),0.U(2.W)).asSInt
  val imm_result_2RI20   = Cat(io.Instruction(24,5),0.U(12.W)).asSInt
  val imm_result_2RI21   = Cat(io.Instruction(4, 0), io.Instruction(27, 10)).asSInt
  val imm_result_I26     = Cat(io.Instruction(9, 0), io.Instruction(27, 10)).asSInt
  val imm_result_ui12    = Cat(0.U(20.W),io.Instruction(21, 10)).asSInt
  val imm_result_ui5     = Cat(0.U(27.W),io.Instruction(14,10)).asSInt
  val imm_result_offs16  = Cat(io.Instruction(25,10),0.U(2.W)).asSInt
  val imm_result_offs26  = Cat(io.Instruction(9,0),io.Instruction(25,10),0.U(2.W)).asSInt
  val imm_result = MuxLookup(
    io.imm_option,
    0.S(Data_width.W))(
    Seq(
      imm_default -> imm_result_default,
      imm_2RI8 -> imm_result_2RI8,
      imm_2RI12 -> imm_result_2RI12,
      imm_2RI14 -> imm_result_2RI14,
      imm_2RI16 -> imm_result_2RI16,
      imm_2RI20 -> imm_result_2RI20, 
      imm_2RI21 -> imm_result_2RI21,
      imm_I26 -> imm_result_I26,
      imm_ui12 -> imm_result_ui12,
      imm_ui5  -> imm_result_ui5,
      imm_offs16 -> imm_result_offs16,
      imm_offs26 -> imm_result_offs26
    )
  )
  io.Out_Eximm := imm_result.asUInt
}
