// package CPU
import chisel3._
import chisel3.util._
import def_GRGDataSel_option.GRGDataSel_option_alu
import def_GRGDataSel_option.GRGDataSel_option_ram
import def_GRGDataSel_option.GRGDataSel_option_pc_4

class Read_GRG extends Module{
    val io = IO(new Read_GRG_interface())
    // val if_rj_addr_mate = Mux((io.rj_addr === io.Exec_wb_rd_addr | io.rj_addr === io.MemoryAccess_wb_rd_addr | io.rj_addr === io.WB_wb_rd_addr),true.B,false.B)
    // val if_rk_addr_mate = Mux((io.rk_addr === io.Exec_wb_rd_addr | io.rk_addr === io.MemoryAccess_wb_rd_addr | io.rk_addr === io.WB_wb_rd_addr),true.B,false.B)
    // val if_rd_addr_mate = Mux((io.rd_addr === io.Exec_wb_rd_addr | io.rd_addr === io.MemoryAccess_wb_rd_addr | io.rd_addr === io.WB_wb_rd_addr),true.B,false.B)
    val rj_result = MuxLookup(io.rj_addr,io.GRG_rj_in)(Seq(
        io.WB_wb_rd_addr -> Mux(!io.WB_wb_rd_en ,io.GRG_rj_in,MuxLookup(io.WB_mem_GRGDataSel_option,io.GRG_rj_in)(Seq(
                GRGDataSel_option_alu -> io.WB_ALU_data,
                GRGDataSel_option_ram -> io.WB_MEM_data,
                GRGDataSel_option_pc_4 -> (io.WB_now_pc+4.U(32.W))
        ))),
        io.MemoryAccess_wb_rd_addr -> Mux(!io.MemoryAccess_wb_rd_en ,io.GRG_rj_in,MuxLookup(io.MemoryAccess_GRGDataSel_option,io.GRG_rj_in)(Seq(
                GRGDataSel_option_alu -> io.MemoryAccess_ALU_data,
                GRGDataSel_option_ram -> io.MemoryAccess_MEM_data,
                GRGDataSel_option_pc_4 -> (io.MemoryAccess_now_pc+4.U(32.W))
        ))),
        io.Exec_wb_rd_addr -> Mux(!io.Exec_wb_rd_en ,io.GRG_rj_in,MuxLookup(io.Exec_GRGDataSel_option,io.GRG_rj_in)(Seq(
                GRGDataSel_option_alu -> io.Exec_ALU_data,
                GRGDataSel_option_pc_4 ->(io.Exec_now_pc+4.U(32.W))
        ))),
    ))
//note Muxlookup的匹配
    val rk_result =  MuxLookup(io.rk_addr,io.GRG_rk_in)(Seq(
        io.WB_wb_rd_addr -> Mux(!io.WB_wb_rd_en,io.GRG_rk_in,MuxLookup(io.WB_mem_GRGDataSel_option,io.GRG_rk_in)(Seq(
                GRGDataSel_option_alu -> io.WB_ALU_data,
                GRGDataSel_option_ram -> io.WB_MEM_data,
                GRGDataSel_option_pc_4 -> (io.WB_now_pc+4.U(32.W))
        ))),
        io.MemoryAccess_wb_rd_addr -> Mux(!io.MemoryAccess_wb_rd_en,io.GRG_rk_in,MuxLookup(io.MemoryAccess_GRGDataSel_option,io.GRG_rk_in)(Seq(
                GRGDataSel_option_alu -> io.MemoryAccess_ALU_data,
                GRGDataSel_option_ram -> io.MemoryAccess_MEM_data,
                GRGDataSel_option_pc_4 ->( io.MemoryAccess_now_pc+4.U(32.W))
        ))),
        io.Exec_wb_rd_addr -> Mux(!io.Exec_wb_rd_en,io.GRG_rk_in,MuxLookup(io.Exec_GRGDataSel_option,io.GRG_rk_in)(Seq(
                GRGDataSel_option_alu -> io.Exec_ALU_data,
                GRGDataSel_option_pc_4 ->(io.Exec_now_pc+4.U(32.W))
        )))
    ))
    val rd_result =  MuxLookup(io.rd_addr,io.GRG_rd_in)(Seq(
        
        
        io.WB_wb_rd_addr -> Mux(!io.WB_wb_rd_en,io.GRG_rd_in,MuxLookup(io.WB_mem_GRGDataSel_option,io.GRG_rd_in)(Seq(
                GRGDataSel_option_alu -> io.WB_ALU_data,
                GRGDataSel_option_ram -> io.WB_MEM_data,
                GRGDataSel_option_pc_4 -> (io.WB_now_pc+4.U(32.W))
        ))),
        io.MemoryAccess_wb_rd_addr -> Mux(!io.MemoryAccess_wb_rd_en,io.GRG_rd_in,MuxLookup(io.MemoryAccess_GRGDataSel_option,io.GRG_rd_in)(Seq(
                GRGDataSel_option_alu -> io.MemoryAccess_ALU_data,
                GRGDataSel_option_ram -> io.MemoryAccess_MEM_data,
                GRGDataSel_option_pc_4 -> (io.MemoryAccess_now_pc+4.U(32.W))
        ))),
        io.Exec_wb_rd_addr -> Mux(!io.Exec_wb_rd_en,io.GRG_rd_in,MuxLookup(io.Exec_GRGDataSel_option,io.GRG_rd_in)(Seq(
                GRGDataSel_option_alu -> io.Exec_ALU_data,
                GRGDataSel_option_pc_4 ->(io.Exec_now_pc+4.U(32.W))
        ))),
    ))

    io.out_rj := rj_result
    io.out_rk := rk_result
    io.out_rd := rd_result

}


