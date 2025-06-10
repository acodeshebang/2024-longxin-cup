import chisel3._
import chisel3.util._
import scala.reflect.internal.Mode
import scopt.Read

class HaiTangCPU extends Module{
    val io = IO(new HaiTangCPU_interface())

    val PC_obj = Module(new PC())
    val FetchToDecode_obj = Module(new FetchToDecode())

    val imm_obj = Module(new imm())
    val Decode_obj = Module(new Decode())
    val conditional_branch_obj = Module(new conditional_branch())
    val GenericRegisterGroup_obj = Module(new GenericRegisterGroup())
    val Read_GRG_obj = Module(new Read_GRG())

    val DecodeToExec_obj = Module(new DecodeToExec())

    val ALUDataSel_obj = Module(new ALUDataSel())
    val ALU_obj = Module(new ALU())

    val ExecToMemoryAccess_obj = Module(new ExecToMemoryAccess())
    val MemoryAccess_obj = Module(new MemoryAccess())

    val MemoryAccessToWB_obj = Module(new MemoryAccessToWB())
    val GRGDataSel_obj = Module(new GRGDataSel())

    io.pc_addr := PC_obj.io.now_pc
//DecodeToExec_obj.io.out.bits.var_pc_addr//conditional_branch_obj.io.if_Jump //
    PC_obj.io.if_valid := FetchToDecode_obj.io.in.ready & io.if_pc_valid//Mux(DecodeToExec_obj.io.out.bits.if_Jump & DecodeToExec_obj.io.out.valid,true.B,FetchToDecode_obj.io.in.ready & io.if_pc_valid)
    PC_obj.io.alu_out_data := DecodeToExec_obj.io.out.bits.var_pc_addr//conditional_branch_obj.io.out_var_pc_addr//DecodeToExec_obj.io.out.bits.var_pc_addr//conditional_branch_obj.io.out_var_pc_addr//DecodeToExec_obj.io.out.bits.var_pc_addr//ALU_obj.io.out_data//conditional_branch_obj.io.out_var_pc_addr//DecodeToExec_obj.io.out.bits.var_pc_addr//ALU_obj.io.out_data//
    PC_obj.io.pc_sel :=Decode_obj.io.pc_sel //DecodeToExec_obj.io.out.bits.pc_sel //
    PC_obj.io.if_Jump := DecodeToExec_obj.io.out.bits.if_Jump& DecodeToExec_obj.io.out.valid//conditional_branch_obj.io.if_Jump //
    //note
    FetchToDecode_obj.io.in.valid := io.if_pc_valid
    FetchToDecode_obj.io.in.bits.Instruction := io.Instruction
    FetchToDecode_obj.io.in.bits.now_pc := PC_obj.io.now_pc
   // FetchToDecode_obj.io.mem_r := Decode_obj.io.ram_r

    //
    Decode_obj.io.Instruction := FetchToDecode_obj.io.out.bits.Instruction
    conditional_branch_obj.io.conditional_branch_option := Decode_obj.io.conditional_branch_option
    imm_obj.io.Instruction := FetchToDecode_obj.io.out.bits.Instruction
    imm_obj.io.imm_option := Decode_obj.io.imm_option
    conditional_branch_obj.io.conditional_branch_option :=  Decode_obj.io.conditional_branch_option
    GenericRegisterGroup_obj.io.rd_en := MemoryAccessToWB_obj.io.out.bits.rd_en
    GenericRegisterGroup_obj.io.rj_addr := Decode_obj.io.rj_addr
    GenericRegisterGroup_obj.io.rk_addr := Decode_obj.io.rk_addr
    GenericRegisterGroup_obj.io.rd_addr_in := MemoryAccessToWB_obj.io.out.bits.rd_addr
    GenericRegisterGroup_obj.io.rd_addr_out := Decode_obj.io.rd_addr
    GenericRegisterGroup_obj.io.rd_data_in := GRGDataSel_obj.io.GRGDataSel_out_data

    conditional_branch_obj.io.rd_data := Read_GRG_obj.io.out_rd//GenericRegisterGroup_obj.io.rd_data_out
    conditional_branch_obj.io.rj_data := Read_GRG_obj.io.out_rj//GenericRegisterGroup_obj.io.rj_data
    conditional_branch_obj.io.imm := imm_obj.io.Out_Eximm
    conditional_branch_obj.io.now_pc := FetchToDecode_obj.io.out.bits.now_pc


    Read_GRG_obj.io.GRG_rd_in := GenericRegisterGroup_obj.io.rd_data_out
    Read_GRG_obj.io.GRG_rj_in := GenericRegisterGroup_obj.io.rj_data
    Read_GRG_obj.io.GRG_rk_in := GenericRegisterGroup_obj.io.rk_data
    Read_GRG_obj.io.Exec_ALU_data := ALU_obj.io.out_data
    Read_GRG_obj.io.Exec_GRGDataSel_option := DecodeToExec_obj.io.out.bits.GRGDataSel_option
    Read_GRG_obj.io.Exec_wb_rd_addr := DecodeToExec_obj.io.out.bits.rd_addr
    Read_GRG_obj.io.Exec_wb_rd_en := DecodeToExec_obj.io.out.bits.rd_en
    Read_GRG_obj.io.Exec_now_pc := DecodeToExec_obj.io.out.bits.now_pc////////////////////
    Read_GRG_obj.io.MemoryAccess_ALU_data := ExecToMemoryAccess_obj.io.out.bits.alu_out_data
    Read_GRG_obj.io.MemoryAccess_GRGDataSel_option := ExecToMemoryAccess_obj.io.out.bits.MemoryAccess_option
    Read_GRG_obj.io.MemoryAccess_MEM_data := io.r_data
    Read_GRG_obj.io.MemoryAccess_wb_rd_addr := ExecToMemoryAccess_obj.io.out.bits.rd_addr
    Read_GRG_obj.io.MemoryAccess_wb_rd_en := ExecToMemoryAccess_obj.io.out.bits.rd_en
    Read_GRG_obj.io.MemoryAccess_now_pc := ExecToMemoryAccess_obj.io.out.bits.now_pc////////////////
    Read_GRG_obj.io.WB_ALU_data := MemoryAccessToWB_obj.io.out.bits.alu_out_data
    Read_GRG_obj.io.WB_MEM_data := MemoryAccessToWB_obj.io.out.bits.MemoryAccess_out_data
    Read_GRG_obj.io.WB_mem_GRGDataSel_option := MemoryAccessToWB_obj.io.out.bits.GRGDataSel_option
    Read_GRG_obj.io.WB_wb_rd_addr := MemoryAccessToWB_obj.io.out.bits.rd_addr
    Read_GRG_obj.io.WB_wb_rd_en := MemoryAccessToWB_obj.io.out.bits.rd_en
    Read_GRG_obj.io.WB_now_pc := MemoryAccessToWB_obj.io.out.bits.now_pc//////////////////////
    Read_GRG_obj.io.rd_addr := Decode_obj.io.rd_addr
    Read_GRG_obj.io.rj_addr := Decode_obj.io.rj_addr
    Read_GRG_obj.io.rk_addr := Decode_obj.io.rk_addr
    Read_GRG_obj.io.Exec_valid := DecodeToExec_obj.io.out.valid
    Read_GRG_obj.io.MemoryAccess_valid := ExecToMemoryAccess_obj.io.out.valid
    Read_GRG_obj.io.WB_valid := MemoryAccessToWB_obj.io.out.valid


    /////
    //ld 数据冲突
    DecodeToExec_obj.io.DecodeToExec_Data_Conflict.MemoryAccess_mem_r := DecodeToExec_obj.io.out.bits.ram_w
    DecodeToExec_obj.io.DecodeToExec_Data_Conflict.MemoryAccess_rd_addr := DecodeToExec_obj.io.out.bits.rd_addr
    // DecodeToExec_obj.io.DecodeToExec_Data_Conflict.rd_addr := Decode_obj.io.rd_addr
    DecodeToExec_obj.io.DecodeToExec_Data_Conflict.Decode_rj_addr := Decode_obj.io.rj_addr
    DecodeToExec_obj.io.DecodeToExec_Data_Conflict.Decode_rk_addr := Decode_obj.io.rk_addr
    


    //GRGDataSel_obj
    DecodeToExec_obj.io.in.bits.var_pc_addr := conditional_branch_obj.io.out_var_pc_addr
    DecodeToExec_obj.io.in.bits.rd_en := Decode_obj.io.rd_en
    DecodeToExec_obj.io.in.bits.rd_addr := Decode_obj.io.rd_addr
    DecodeToExec_obj.io.in.bits.GRGDataSel_option := Decode_obj.io.GRGDataSel_option
    DecodeToExec_obj.io.in.bits.MemoryAccess_option := Decode_obj.io.MemoryAccess_option
    DecodeToExec_obj.io.in.bits.Out_Eximm := imm_obj.io.Out_Eximm
    DecodeToExec_obj.io.in.bits.alu_a_sel := Decode_obj.io.alu_a_sel
    DecodeToExec_obj.io.in.bits.alu_b_sel := Decode_obj.io.alu_b_sel
    DecodeToExec_obj.io.in.bits.alu_option := Decode_obj.io.alu_option
    DecodeToExec_obj.io.in.bits.if_Jump := conditional_branch_obj.io.if_Jump
    DecodeToExec_obj.io.in.bits.now_pc := FetchToDecode_obj.io.out.bits.now_pc
    DecodeToExec_obj.io.in.bits.pc_sel := Decode_obj.io.pc_sel
    DecodeToExec_obj.io.in.bits.ram_r := Decode_obj.io.ram_r
    DecodeToExec_obj.io.in.bits.ram_w := Decode_obj.io.ram_w
    DecodeToExec_obj.io.in.bits.rd_data_out :=Read_GRG_obj.io.out_rd
    DecodeToExec_obj.io.in.bits.rj_data := Read_GRG_obj.io.out_rj
    DecodeToExec_obj.io.in.bits.rk_data := Read_GRG_obj.io.out_rk

    //
    ALUDataSel_obj.io.alu_a_sel := DecodeToExec_obj.io.out.bits.alu_a_sel
    ALUDataSel_obj.io.alu_b_sel := DecodeToExec_obj.io.out.bits.alu_b_sel
    ALUDataSel_obj.io.imm_data := DecodeToExec_obj.io.out.bits.Out_Eximm
    ALUDataSel_obj.io.rj_data := DecodeToExec_obj.io.out.bits.rj_data
    ALUDataSel_obj.io.rk_data := DecodeToExec_obj.io.out.bits.rk_data
    ALUDataSel_obj.io.now_pc := DecodeToExec_obj.io.out.bits.now_pc


    ALU_obj.io.in_data1 := ALUDataSel_obj.io.alu_a_data
    ALU_obj.io.in_data2 := ALUDataSel_obj.io.alu_b_data
    ALU_obj.io.alu_option := DecodeToExec_obj.io.out.bits.alu_option
    ExecToMemoryAccess_obj.io.in.bits.GRGDataSel_option := DecodeToExec_obj.io.out.bits.GRGDataSel_option
    ExecToMemoryAccess_obj.io.in.bits.MemoryAccess_option := DecodeToExec_obj.io.out.bits.MemoryAccess_option
    ExecToMemoryAccess_obj.io.in.bits.alu_out_data := ALU_obj.io.out_data
    ExecToMemoryAccess_obj.io.in.bits.if_Jump := DecodeToExec_obj.io.out.bits.if_Jump
    ExecToMemoryAccess_obj.io.in.bits.pc_sel := DecodeToExec_obj.io.out.bits.pc_sel
    ExecToMemoryAccess_obj.io.in.bits.ram_r := DecodeToExec_obj.io.out.bits.ram_r
    ExecToMemoryAccess_obj.io.in.bits.ram_w := DecodeToExec_obj.io.out.bits.ram_w
    ExecToMemoryAccess_obj.io.in.bits.rd_data_out := DecodeToExec_obj.io.out.bits.rd_data_out
    ExecToMemoryAccess_obj.io.in.bits.rd_addr := DecodeToExec_obj.io.out.bits.rd_addr
    ExecToMemoryAccess_obj.io.in.bits.rd_en := DecodeToExec_obj.io.out.bits.rd_en
    ExecToMemoryAccess_obj.io.in.bits.now_pc := DecodeToExec_obj.io.out.bits.now_pc

    //
    MemoryAccess_obj.io.MemoryAccess_option := ExecToMemoryAccess_obj.io.out.bits.MemoryAccess_option
    MemoryAccess_obj.io.MemoryAccess_in := ExecToMemoryAccess_obj.io.out.bits.alu_out_data
    io.r_ram_be_n := MemoryAccess_obj.io.ram_be_n
    io.w_data := ExecToMemoryAccess_obj.io.out.bits.rd_data_out
    io.r_addr := MemoryAccess_obj.io.MemoryAccess_out
    io.w_addr := MemoryAccess_obj.io.MemoryAccess_out
    io.r_en := ExecToMemoryAccess_obj.io.out.bits.ram_r
    io.w_en := ExecToMemoryAccess_obj.io.out.bits.ram_w
    
    MemoryAccessToWB_obj.io.in.bits.GRGDataSel_option := ExecToMemoryAccess_obj.io.out.bits.GRGDataSel_option
    MemoryAccessToWB_obj.io.in.bits.MemoryAccess_out_data := io.r_data
    MemoryAccessToWB_obj.io.in.bits.alu_out_data := ExecToMemoryAccess_obj.io.out.bits.alu_out_data
    MemoryAccessToWB_obj.io.in.bits.if_Jump := ExecToMemoryAccess_obj.io.out.bits.if_Jump
    MemoryAccessToWB_obj.io.in.bits.pc_sel := ExecToMemoryAccess_obj.io.out.bits.pc_sel
    MemoryAccessToWB_obj.io.in.bits.rd_addr := ExecToMemoryAccess_obj.io.out.bits.rd_addr
    MemoryAccessToWB_obj.io.in.bits.rd_en := ExecToMemoryAccess_obj.io.out.bits.rd_en
    MemoryAccessToWB_obj.io.in.bits.now_pc := ExecToMemoryAccess_obj.io.out.bits.now_pc

    //
    GRGDataSel_obj.io.GRGDataSel_option := MemoryAccessToWB_obj.io.out.bits.GRGDataSel_option
    GRGDataSel_obj.io.MemoryAccess_out_data := MemoryAccessToWB_obj.io.out.bits.MemoryAccess_out_data
    GRGDataSel_obj.io.alu_out_data := MemoryAccessToWB_obj.io.out.bits.alu_out_data
    GRGDataSel_obj.io.now_pc := MemoryAccessToWB_obj.io.out.bits.now_pc
 

    FetchToDecode_obj.io.out.ready := DecodeToExec_obj.io.in.ready
    DecodeToExec_obj.io.in.valid := FetchToDecode_obj.io.out.valid 

    DecodeToExec_obj.io.out.ready := ExecToMemoryAccess_obj.io.in.ready
    ExecToMemoryAccess_obj.io.in.valid := DecodeToExec_obj.io.out.valid

    ExecToMemoryAccess_obj.io.out.ready := MemoryAccessToWB_obj.io.in.ready
    MemoryAccessToWB_obj.io.in.valid := ExecToMemoryAccess_obj.io.out.valid

    MemoryAccessToWB_obj.io.out.ready := true.B


    //控制冲突//!conditional_branch_obj.io.if_Jump//
    FetchToDecode_obj.io.if_var_pc_Conflict := !DecodeToExec_obj.io.out.bits.if_Jump
    DecodeToExec_obj.io.if_var_pc_Conflict := !DecodeToExec_obj.io.out.bits.if_Jump


     
}