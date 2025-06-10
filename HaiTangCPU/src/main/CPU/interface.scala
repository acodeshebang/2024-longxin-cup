import chisel3._
import width._
import java.util.ResourceBundle
class PC_interface extends Bundle{
    val if_Jump = Input(Bool())
    val now_pc = Output(UInt(pc_width.W))
    val if_valid = Input(Bool()) 
    val alu_out_data = Input(UInt(Data_width.W))
    val pc_sel = Input(UInt(pc_sel_width.W))

}
class imm_interface extends Bundle{
    val Instruction = Input(UInt(Instruction_width.W))
    val Out_Eximm = Output(UInt(Data_width.W))
    val imm_option = Input(UInt(imm_option_width.W))
}

class ALU_interface extends  Bundle{
    val in_data1 = Input(UInt(Data_width.W))
    val in_data2 = Input(UInt(Data_width.W))
    val alu_option = Input(UInt(alu_option_width.W))
    val out_data = Output(UInt(Data_width.W))
}
class ALUDataSel_interface extends Bundle{
    val alu_a_sel = Input(UInt(alu_a_sel_width.W))
    val alu_b_sel = Input(UInt(alu_b_sel_width.W))
    val imm_data = Input(UInt(Data_width.W))
    val rj_data = Input(UInt(Data_width.W))
    val rk_data = Input(UInt(Data_width.W))
    val now_pc = Input(UInt(pc_width.W))
    val alu_a_data = Output(UInt(Data_width.W))
    val alu_b_data = Output(UInt(Data_width.W))
}
class GenericRegisterGroup_interface extends Bundle {
  val rd_en    = Input(Bool())
  val rd_addr_in  = Input(UInt(reg_addr_width.W))
  val rd_addr_out = Input(UInt(reg_addr_width.W))
  val rj_addr = Input(UInt(reg_addr_width.W))
  val rk_addr = Input(UInt(reg_addr_width.W))
  val rd_data_in  = Input(UInt(reg_width.W))
  val rj_data = Output(UInt(reg_width.W))
  val rk_data = Output(UInt(reg_width.W))
  val rd_data_out = Output(UInt(reg_width.W))
}
class HaiTangCPU_interface extends  Bundle{
    val pc_addr = Output(UInt(pc_width.W))
    val Instruction = Input(UInt(Instruction_width.W))
    val r_addr = Output(UInt(Data_addr_width.W))
    val r_data = Input(UInt(Data_addr_width.W))
    val r_en = Output(Bool())
    val w_addr = Output(UInt(Data_addr_width.W))
    val w_data = Output(UInt(Data_addr_width.W))
    val w_en = Output(Bool())
    val if_pc_valid = Input(Bool())
    val r_ram_be_n = Output(UInt(4.W))
    // val wr_valid = Input(Bool())
}
class conditional_branch_interface extends Bundle{
    val conditional_branch_option = Input(UInt(conditional_branch_option_width.W))
    val if_Jump = Output(Bool())
    val rj_data = Input(UInt(Data_width.W))
    val rd_data = Input(UInt(Data_width.W))
    val now_pc = Input(UInt(pc_width.W))
    val imm = Input(UInt(Data_width.W))
    val out_var_pc_addr = Output(UInt(pc_width.W))
}
class Decode_interface extends  Bundle{
    val Instruction = Input(UInt(Instruction_width.W))

    val imm_option = Output(UInt(imm_option_width.W))
    val conditional_branch_option = Output(UInt(conditional_branch_option_width.W))
    val alu_option = Output(UInt(alu_option_width.W))
    val MemoryAccess_option = Output(UInt(MemoryAccess_option_width.W))
    val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    val alu_a_sel = Output(UInt(alu_a_sel_width.W))
    val alu_b_sel = Output(UInt(alu_b_sel_width.W))
    val pc_sel = Output(UInt(pc_sel_width.W))
    val ram_r   = Output(Bool())
    val ram_w   = Output(Bool())
    val rd_en = Output(Bool())

    val rd_addr = Output(UInt(reg_addr_width.W))
    val rj_addr = Output(UInt(reg_addr_width.W))
    val rk_addr = Output(UInt(reg_addr_width.W))
   
    
}
class MemoryAccess_interface extends Bundle{
    val ram_be_n = Output(UInt(ram_be_n_width.W))
    val MemoryAccess_option = Input(UInt(MemoryAccess_option_width.W))
    val MemoryAccess_in = Input(UInt(MemoryAccess_width.W))
    val MemoryAccess_out = Output(UInt(MemoryAccess_width.W))
}
class GRGDataSel_interface extends Bundle{
    val alu_out_data = Input(UInt(Data_width.W))
    val MemoryAccess_out_data = Input(UInt(MemoryAccess_width.W))
    val  GRGDataSel_option = Input(UInt(GRGDataSel_option_width.W))
    val GRGDataSel_out_data = Output(UInt(Data_width.W))
    val now_pc   = Input(UInt(pc_width.W))
}
//流水线
class FetchToDecode_interface extends  Bundle{
    val Instruction = Output(UInt(Instruction_width.W))
    val now_pc = Output(UInt(pc_width.W))
}
class DecodeToExec_interface extends Bundle{
    val alu_option = Output(UInt(alu_option_width.W))
    val MemoryAccess_option = Output(UInt(MemoryAccess_option_width.W))
    val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    val alu_a_sel = Output(UInt(alu_a_sel_width.W))
    val alu_b_sel = Output(UInt(alu_b_sel_width.W))
    val pc_sel = Output(UInt(pc_sel_width.W))
    val ram_r   = Output(Bool())
    val ram_w   = Output(Bool())
    val Out_Eximm = Output(UInt(Data_width.W))
    val rj_data = Output(UInt(reg_width.W))
    val rk_data = Output(UInt(reg_width.W))
    val rd_data_out = Output(UInt(reg_width.W))
    val now_pc = Output(UInt(pc_width.W))
    val if_Jump = Output(Bool())
    val rd_addr = Output(UInt(reg_addr_width.W))
    val rd_en = Output(Bool())
    val var_pc_addr = Output(UInt(pc_width.W))
}
class ExecToMemoryAccess_interface extends Bundle{
    val MemoryAccess_option = Output(UInt(MemoryAccess_option_width.W))
    val alu_out_data = Output(UInt(Data_width.W))
    val pc_sel = Output(UInt(pc_sel_width.W))
    val ram_r   = Output(Bool())
    val ram_w   = Output(Bool())
    val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    val rd_data_out = Output(UInt(reg_width.W))
    val if_Jump = Output(Bool())
    val rd_addr = Output(UInt(reg_addr_width.W))
    val rd_en = Output(Bool())
    val now_pc = Output(UInt(pc_width.W))
}
class MemoryAccessToWB_interface extends Bundle{
    val GRGDataSel_option = Output(UInt(GRGDataSel_option_width.W))
    val alu_out_data = Output(UInt(Data_width.W))
    val MemoryAccess_out_data = Output(UInt(Data_width.W))
    val pc_sel = Output(UInt(pc_sel_width.W))
    val if_Jump = Output(Bool())
    val rd_addr = Output(UInt(reg_addr_width.W))
    val rd_en = Output(Bool())
    val now_pc = Output(UInt(pc_width.W))
}

class Read_GRG_interface extends  Bundle {
    val out_rj = Output(UInt(Data_width.W))
    val out_rk = Output(UInt(Data_width.W))
    val out_rd = Output(UInt(Data_width.W))
    
    val GRG_rj_in = Input(UInt(Data_width.W))
    val GRG_rk_in = Input(UInt(Data_width.W))
    val GRG_rd_in = Input(UInt(Data_width.W))

    val rj_addr = Input(UInt(reg_addr_width.W))
    val rk_addr = Input(UInt(reg_addr_width.W))
    val rd_addr = Input(UInt(reg_addr_width.W))
    
    //Exec
    val Exec_wb_rd_addr = Input(UInt(reg_addr_width.W))
    val Exec_wb_rd_en = Input(Bool())
    val Exec_ALU_data = Input(UInt(Data_width.W))
    val Exec_GRGDataSel_option = Input(UInt(GRGDataSel_option_width.W))
    val Exec_valid = Input(Bool())
    val Exec_now_pc = Input(UInt(pc_width.W))

    //MemoryAccess
    val MemoryAccess_ALU_data = Input(UInt(Data_width.W))
    val MemoryAccess_wb_rd_addr = Input(UInt(reg_addr_width.W))
    val MemoryAccess_wb_rd_en = Input(Bool())
    val MemoryAccess_MEM_data = Input(UInt(Data_width.W))
    val MemoryAccess_GRGDataSel_option = Input(UInt(GRGDataSel_option_width.W))
    val MemoryAccess_valid = Input(Bool())
    val MemoryAccess_now_pc = Input(UInt(pc_width.W))

    //WB
    val WB_ALU_data = Input(UInt(Data_width.W))
    val WB_wb_rd_addr = Input(UInt(reg_addr_width.W))
    val WB_wb_rd_en = Input(Bool())
    val WB_MEM_data = Input(UInt(Data_width.W))
    val WB_mem_GRGDataSel_option = Input(UInt(GRGDataSel_option_width.W))
    val WB_valid = Input(Bool())
    val WB_now_pc = Input(UInt(pc_width.W))


}

class DecodeToExec_Data_Conflict_interface extends Bundle{
    val MemoryAccess_mem_r = Input(Bool())
    val MemoryAccess_rd_addr = Input(UInt(reg_addr_width.W))
    val Decode_rk_addr = Input(UInt(reg_addr_width.W))
    val Decode_rj_addr = Input(UInt(reg_addr_width.W))
}