import chisel3._
import chisel3.util._
import Decode_define._
import width._
class Decode extends RawModule{
    val io = IO(new Decode_interface())
    

    val signal = ListLookup(io.Instruction,Decode_signal_default,map)

    io.imm_option := signal(0)
    io.conditional_branch_option := signal(1)
    io.alu_option := signal(2)
    io.MemoryAccess_option := signal(3)
    io.GRGDataSel_option := signal(4)
    io.alu_a_sel := signal(5)
    io.alu_b_sel := signal(6)
    io.pc_sel := signal(7)
    io.ram_r := signal(8)
    io.ram_w := signal(9)
    io.rd_en := signal(10)

    io.rk_addr := io.Instruction(14,10)
    io.rj_addr := io.Instruction(9,5)
    io.rd_addr := Mux(io.Instruction===BL,1.U,io.Instruction(4,0))
    
}