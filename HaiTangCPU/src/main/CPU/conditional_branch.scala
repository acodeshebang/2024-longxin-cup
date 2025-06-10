import chisel3._
import chisel3.util._
import width._
import def_conditional_branch_option._

class conditional_branch extends RawModule{
    val io = IO(new conditional_branch_interface())

    val conditional_branch_result_default=false.B
    val conditional_branch_result_bne = Mux(io.rj_data=/=io.rd_data,true.B,false.B)//sign
    val conditional_branch_result_beq = Mux(io.rj_data===io.rd_data,true.B,false.B)
    val conditional_branch_result_nif = true.B
    val conditional_branch_result_BGEU = Mux(io.rj_data.asUInt > io.rd_data.asUInt,true.B,false.B)
    val conditional_branch_result = MuxLookup(io.conditional_branch_option,conditional_branch_result_default)(Seq(
        conditional_branch_default -> conditional_branch_result_default,
        conditional_branch_bne -> conditional_branch_result_bne,
        conditional_branch_beq -> conditional_branch_result_beq,
        conditional_branch_nif -> conditional_branch_result_nif,
        conditional_branch_BGEU -> conditional_branch_result_BGEU,
        conditional_branch_jirl -> conditional_branch_result_nif
    ))
    io.if_Jump := conditional_branch_result

    val conditional_branch_pc_var_default = 0.U
    val conditional_branch_pc_var_pc_imm = io.now_pc + io.imm
    val conditional_branch_pc_var_rj_imm = io.rj_data + io.imm
    val conditional_branch_pc_var_result = MuxLookup(io.conditional_branch_option,conditional_branch_pc_var_default)(Seq(
        conditional_branch_default -> conditional_branch_pc_var_default,
        conditional_branch_bne    -> conditional_branch_pc_var_pc_imm,
        conditional_branch_beq    -> conditional_branch_pc_var_pc_imm,
        conditional_branch_nif           -> conditional_branch_pc_var_pc_imm,
        conditional_branch_jirl          -> conditional_branch_pc_var_rj_imm
    ))
    //val var_pc_addr = io.now_pc.asSInt + io.imm.asSInt
    io.out_var_pc_addr := conditional_branch_pc_var_result.asUInt
}