import chisel3._
import chisel3.util._
import width._
import def_imm_option._
import def_alu_option._
import def_pc_sel._
import def_conditional_branch_option._
import alu_a_sel._
import alu_b_sel._
import def_MemoryAccess_option._
import def_GRGDataSel_option._

object def_imm_option{
    val imm_default = 0.U(imm_option_width.W)
    val imm_2RI8 = 1.U(imm_option_width.W)
    val imm_2RI12 = 2.U(imm_option_width.W)
    val imm_2RI14 = 3.U(imm_option_width.W)
    val imm_2RI16 = 4.U(imm_option_width.W)
    val imm_2RI20 = 5.U(imm_option_width.W)
    val imm_2RI21 = 6.U(imm_option_width.W)
    val imm_I26 = 7.U(imm_option_width.W)

    val imm_ui12 = 8.U(imm_option_width.W)
    val imm_ui5  = 9.U(imm_option_width.W)
    val imm_offs16 = 10.U(imm_option_width.W)
    val imm_offs26 = 11.U(imm_option_width.W)
}
object def_alu_option{
    val alu_default = 0.U(Data_width.W)
    val alu_mov = 0.U(Data_width.W)
    val alu_add = 1.U(Data_width.W)
    val alu_sub = 2.U(Data_width.W)
    val alu_or = 3.U(Data_width.W)
    val alu_xor = 4.U(Data_width.W)
    val alu_and = 5.U(Data_width.W)
    val alu_srl = 6.U(Data_width.W)
    val alu_sll = 7.U(Data_width.W)
    val alu_mul = 8.U(Data_width.W)
    val alu_SLTI = 9.U(Data_width.W)
}


object def_pc_sel {
    val pc_sel_default = 0.U(pc_sel_width.W)
    val pc_sel_alu_out = 0.U(pc_sel_width.W)
}

object def_conditional_branch_option{
    val conditional_branch_default = 0.U(conditional_branch_option_width.W) 
    val conditional_branch_bne = 1.U(conditional_branch_option_width.W)
    val conditional_branch_beq = 2.U(conditional_branch_option_width.W)
    val conditional_branch_nif = 3.U(conditional_branch_option_width.W)
    val conditional_branch_BGEU = 4.U(conditional_branch_option_width.W)
    val conditional_branch_jirl =5.U(conditional_branch_option_width.W)
}

object alu_a_sel{
    val alu_a_sel_default = 0.U(alu_a_sel_width.W)
    val alu_a_sel_rj = 0.U(alu_a_sel_width.W)
    val alu_a_sel_pc = 1.U(alu_a_sel_width.W)
    
}
object alu_b_sel{
    val alu_b_sel_default = 0.U(alu_b_sel_width.W)
    val alu_b_sel_rk = 0.U(alu_b_sel_width.W)
    val alu_b_sel_imm = 1.U(alu_b_sel_width.W)
}
object def_MemoryAccess_option{
    val MemoryAccess_option_default = 0.U(MemoryAccess_option_width.W)
    val MemoryAccess_option_8bit =1.U(MemoryAccess_option_width.W)
    val MemoryAccess_option_32bit = 2.U(MemoryAccess_option_width.W)
    //val Me
}
object def_GRGDataSel_option{
    val GRGDataSel_option_default = 0.U(GRGDataSel_option_width.W)
    val GRGDataSel_option_alu = 0.U(GRGDataSel_option_width.W)
    val GRGDataSel_option_ram = 2.U(GRGDataSel_option_width.W)
    val GRGDataSel_option_pc_4 = 3.U(GRGDataSel_option_width.W)
}
object Decode_define{
    
    val addi_w = BitPat("b0000001010??????????????????????")
    val ori    = BitPat("b0000001110??????????????????????")
    val andi   = BitPat("b0000001101??????????????????????")
    val lu12I_w =BitPat("b0001010?????????????????????????")
    val pcaddu12i=BitPat("b0001110?????????????????????????")
    val slli_w   =BitPat("b00000000010000001???????????????")
    val srli_w   =BitPat("b00000000010001001???????????????")
    
    
    val add_w   =BitPat("b00000000000100000???????????????")
    val sub_w   =BitPat("b00000000000100010???????????????")
    val or      =BitPat("b00000000000101010???????????????")
    val and     =BitPat("b00000000000101001???????????????")
    val xor     =BitPat("b00000000000101011???????????????")
    
 
    val st_w    =BitPat("b0010100110??????????????????????")
    val ld_w    =BitPat("b0010100010??????????????????????")
    val st_b    =BitPat("b0010100100??????????????????????")
    val ld_b    =BitPat("b0010100000??????????????????????")
    val bne     =BitPat("b010111??????????????????????????")
    val beq     =BitPat("b010110??????????????????????????")
    val jirl    =BitPat("b010011??????????????????????????")
    val B       =BitPat("b010100??????????????????????????")
    val BL      =BitPat("b010101??????????????????????????")

    val mul_w   =BitPat("b00000000000111000???????????????")

    //ext
    val SLTI    =BitPat("b0000001000??????????????????????")
    val SLL_W   =BitPat("b00000000000101110???????????????") 
    val BGEU    =BitPat("b011011??????????????????????????")
    
    // imm_option  conditional_branch_option ALU_option MemoryAccess_option GRGDataSel_option alu_a_sel alu_b_sel pc_sel ram_r ram_w rd_en
    val Decode_signal_default = List(imm_default,conditional_branch_default,alu_default,MemoryAccess_option_default,GRGDataSel_option_default,alu_a_sel_default,alu_b_sel_default,pc_sel_default,false.B,false.B,false.B)
    val map = Array(
        addi_w -> List(imm_2RI12,   conditional_branch_default, alu_add,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        ori    -> List(imm_ui12 ,   conditional_branch_default, alu_or ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        andi   -> List(imm_ui12 ,   conditional_branch_default, alu_and ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        slli_w -> List(imm_ui5  ,   conditional_branch_default, alu_sll ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        srli_w -> List(imm_ui5  ,   conditional_branch_default, alu_srl ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        lu12I_w -> List(imm_2RI20,  conditional_branch_default, alu_mov,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_default,      alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        pcaddu12i->List(imm_2RI20,  conditional_branch_default, alu_add,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_pc,            alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),

        add_w  -> List(imm_default, conditional_branch_default, alu_add,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        sub_w  -> List(imm_default, conditional_branch_default, alu_sub,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        or     -> List(imm_default, conditional_branch_default, alu_or ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        and    -> List(imm_default, conditional_branch_default, alu_and ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        xor    -> List(imm_default, conditional_branch_default, alu_xor ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        mul_w  -> List(imm_default, conditional_branch_default, alu_mul ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),

        bne     -> List(imm_2RI16,  conditional_branch_bne,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_default,      alu_a_sel_pc,           alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    false.B),
        beq     -> List(imm_2RI16,  conditional_branch_beq,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_default,      alu_a_sel_pc,           alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    false.B),
        B       -> List(imm_offs26,  conditional_branch_nif,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_default,      alu_a_sel_pc,        alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    false.B),
        BL      -> List(imm_offs26,  conditional_branch_nif,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_pc_4,      alu_a_sel_pc,           alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    true.B),
        jirl    -> List(imm_offs16,  conditional_branch_jirl,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_pc_4,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    true.B),    
        
        st_w    -> List(imm_2RI12,  conditional_branch_default, alu_add,    MemoryAccess_option_32bit,      GRGDataSel_option_default,  alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    true.B,     false.B),
        st_b    -> List(imm_2RI12,  conditional_branch_default, alu_add,    MemoryAccess_option_8bit ,      GRGDataSel_option_default,  alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    true.B,     false.B),
        ld_w    -> List(imm_2RI12,  conditional_branch_default, alu_add,    MemoryAccess_option_32bit,      GRGDataSel_option_ram,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, true.B,     false.B,    true.B),
        ld_b    -> List(imm_2RI12,  conditional_branch_default, alu_add,    MemoryAccess_option_8bit ,      GRGDataSel_option_ram,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, true.B,     false.B,    true.B),
    
        // BGEU    -> List(imm_2RI16,  conditional_branch_BGEU,     alu_add,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_pc,           alu_b_sel_imm,  pc_sel_alu_out, false.B,    false.B,    false.B),
        // SLL_W   -> List(imm_default, conditional_branch_default, alu_sll,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_rk,   pc_sel_default, false.B,    false.B,    true.B),
        // SLTI    -> List(imm_2RI12  , conditional_branch_default, alu_SLTI ,    MemoryAccess_option_default,    GRGDataSel_option_alu,      alu_a_sel_rj,           alu_b_sel_imm,  pc_sel_default, false.B,    false.B,    true.B),
        )
}
