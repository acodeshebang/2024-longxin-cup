import chisel3._
object width{
    def Data_width = 32
    def pc_width = 32
    def Data_addr_width = 32
    def imm_option_width = 4
    def Instruction_width = 32
    def alu_option_width = 4
    def conditional_branch_option_width = 4
    def reg_addr_width = 5
    def reg_width = 32
    def ram_be_n_width = 4
    def MemoryAccess_width = 32
    def alu_a_sel_width = 2
    def alu_b_sel_width = 2
    def reg_count = 32
    def pc_sel_width = 2
    def MemoryAccess_option_width = 3
    def GRGDataSel_option_width = 2
}