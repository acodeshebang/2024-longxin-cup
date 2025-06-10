import chisel3._
import chisel3.util._
import def_MemoryAccess_option.MemoryAccess_option_default
import def_MemoryAccess_option.MemoryAccess_option_8bit
import def_MemoryAccess_option.MemoryAccess_option_32bit

class MemoryAccess extends Module{
    val io =IO(new MemoryAccess_interface())
    val ram_be_n_default = 0.U(4.W)
    io.ram_be_n := MuxLookup(io.MemoryAccess_option,ram_be_n_default)(Seq(
        MemoryAccess_option_default -> ram_be_n_default,
        MemoryAccess_option_8bit    -> MuxLookup(io.MemoryAccess_in(1,0),14.U(4.W))(Seq(
            0.U(2.W) -> 14.U(4.W),
            1.U(2.W) -> 13.U(4.W),
            2.U(2.W) -> 11.U(4.W),
            3.U(2.W) -> 7.U(4.W)
        )),
        MemoryAccess_option_32bit -> 0.U(4.W)
    ))
    io.MemoryAccess_out := io.MemoryAccess_in
    
}

