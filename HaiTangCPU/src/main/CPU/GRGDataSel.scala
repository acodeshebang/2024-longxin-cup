import chisel3._
import chisel3.util._
import def_GRGDataSel_option._
class GRGDataSel extends Module{
    val io = IO(new GRGDataSel_interface())

    val GRGDataSel_result = MuxLookup(io.GRGDataSel_option,GRGDataSel_option_default)(Seq(
        GRGDataSel_option_default -> io.alu_out_data,
        GRGDataSel_option_alu     -> io.alu_out_data,
        GRGDataSel_option_ram     -> io.MemoryAccess_out_data,
        GRGDataSel_option_pc_4    -> (io.now_pc + 4.U(32.W))//
    ))
    io.GRGDataSel_out_data := GRGDataSel_result
}