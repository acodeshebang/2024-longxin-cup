import chisel3._
import chisel3.util._
import width._
import alu_a_sel._
import alu_b_sel._

class ALUDataSel extends RawModule{
    val io = IO(new ALUDataSel_interface())

    val alu_a_data_result = MuxLookup(io.alu_a_sel,io.rj_data)(Seq(
        alu_a_sel_default -> io.rj_data,
        alu_a_sel_rj -> io.rj_data,
        alu_a_sel_pc -> io.now_pc,
        
    ))

    val alu_b_data_result = MuxLookup(io.alu_b_sel,io.rk_data)(Seq(
        alu_b_sel_default -> io.rk_data,
        alu_b_sel_rk -> io.rk_data,
        alu_b_sel_imm -> io.imm_data
    ))

    io.alu_a_data := alu_a_data_result
    io.alu_b_data := alu_b_data_result
}