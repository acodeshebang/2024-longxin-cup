import chisel3._
import chisel3.util._
//import java.io.Serial._

class Memctl extends Module{
    val io = IO(new Bundle{
        //BaseRAM信号
        val base_ram_data_in = Input(UInt(32.W))
        val base_ram_data_out = Output(UInt(32.W))
        val base_ram_addr = Output(UInt(20.W))
        val base_ram_be_n = Output(UInt(4.W))
        val base_ram_ce_n = Output(Bool())
        val base_ram_oe_n = Output(Bool())
        val base_ram_we_n = Output(Bool())
        val if_base_ram_in = Output(Bool())

        //ExtRAM信号 
        val ext_ram_data_in = Input(UInt(32.W))
        val ext_ram_data_out = Output(UInt(32.W))
        val ext_ram_addr = Output(UInt(20.W))
        val ext_ram_be_n = Output(UInt(4.W))
        val ext_ram_ce_n = Output(Bool())
        val ext_ram_oe_n = Output(Bool())
        val ext_ram_we_n = Output(Bool())
        val if_ext_ram_in = Output(Bool())

        val pc_addr = Input(UInt(32.W))
        val if_pc_valid = Output(Bool())
        val Instruction = Output(UInt(32.W))
        val r_ram_be_n = Input(UInt(4.W))

        val mem_read_addr = Input(UInt(32.W))
        val mem_read_Data = Output(UInt(32.W))
        val mem_write_Data = Input(UInt(32.W))
        val mem_write_addr = Input(UInt(32.W))
        val mem_read_en = Input(Bool())
        val mem_write_en = Input(Bool())

        val txd = Output(Bool())
        val rxd = Input(Bool())

        val serial_clock = Input(Clock())
        val serial_rst   = Input(Bool())


    })
    val Serial_obj = Module(new Serial())
    val BaseRAM_Addr=0.U(2.W)
    val ExtRAM_Addr=1.U(2.W)
    val Serial_Addr=2.U(2.W)
    val no_map_addr = 3.U(2.W)
    val MemoryAccess_addr = Mux(io.mem_read_en,io.mem_read_addr,io.mem_write_addr)
    val mem_addr_judgment = Mux((MemoryAccess_addr>="h80000000".U(32.W) & MemoryAccess_addr<="h803fffff".U(32.W)),BaseRAM_Addr,Mux((MemoryAccess_addr>="h80400000".U(32.W) & MemoryAccess_addr<="h807fffff".U(32.W)),ExtRAM_Addr,Mux((MemoryAccess_addr==="hBFd003f8".U(32.W) | MemoryAccess_addr === "hBFD003FC".U(32.W)),Serial_Addr,no_map_addr)))
    io.if_pc_valid := Mux((mem_addr_judgment===BaseRAM_Addr &(io.mem_read_en | io.mem_write_en)),false.B,true.B)& !Serial_obj.io.serial_pc_stop
    io.base_ram_ce_n := true.B
    io.base_ram_oe_n := Mux((mem_addr_judgment===BaseRAM_Addr & io.mem_write_en),false.B,true.B)
    io.base_ram_we_n := Mux((mem_addr_judgment===BaseRAM_Addr & io.mem_write_en),true.B,false.B)
    io.if_base_ram_in := Mux((mem_addr_judgment===BaseRAM_Addr &( io.mem_write_en)),false.B,true.B)
    io.base_ram_data_out := io.mem_write_Data
    io.base_ram_be_n := Mux((mem_addr_judgment===BaseRAM_Addr & (io.mem_read_en | io.mem_write_en)),io.r_ram_be_n,0.U(4.W))
    io.base_ram_addr := Mux((mem_addr_judgment===BaseRAM_Addr & (io.mem_read_en | io.mem_write_en)),MemoryAccess_addr(21,2),io.pc_addr(21,2))
    io.Instruction := Mux((mem_addr_judgment===BaseRAM_Addr & (io.mem_read_en | io.mem_write_en)),"h00000000".U(32.W),io.base_ram_data_in)
    
    io.ext_ram_be_n := io.r_ram_be_n
    io.ext_ram_ce_n := Mux(mem_addr_judgment===ExtRAM_Addr&(io.mem_read_en| io.mem_write_en),true.B,false.B)
    io.ext_ram_oe_n := Mux((mem_addr_judgment===ExtRAM_Addr&io.mem_read_en),true.B,false.B)
    io.ext_ram_we_n := Mux((mem_addr_judgment===ExtRAM_Addr&io.mem_write_en),true.B,false.B)
    io.if_ext_ram_in := io.ext_ram_oe_n
    io.ext_ram_addr := MemoryAccess_addr(21,2)
    io.ext_ram_data_out := io.mem_write_Data
    val mem_read_Data_tmp = Wire(UInt(32.W))
    
    mem_read_Data_tmp:= Mux(io.mem_read_en,Mux((mem_addr_judgment===BaseRAM_Addr),io.base_ram_data_in,Mux(mem_addr_judgment===ExtRAM_Addr,io.ext_ram_data_in,Mux(mem_addr_judgment===Serial_Addr,Serial_obj.io.Serial_read_Data,0.U(32.W)))),0.U(32.W))//Mux(io.mem_read_en, ,mem_read_Data_tmp)
    io.mem_read_Data := MuxLookup(io.r_ram_be_n, mem_read_Data_tmp)(Seq(
        0.U(4.W) -> mem_read_Data_tmp,
        14.U(4.W) -> (Cat(mem_read_Data_tmp(7,0)).asSInt).asUInt,
        13.U(4.W) -> (Cat(mem_read_Data_tmp(15,8)).asSInt).asUInt,
        11.U(4.W) -> (Cat(mem_read_Data_tmp(23,16)).asSInt).asUInt,
        7.U(4.W) -> (Cat(mem_read_Data_tmp(31,24)).asSInt).asUInt,
    ))
    Serial_obj.io.RxD := io.rxd
    io.txd := Serial_obj.io.TxD
    Serial_obj.io.r_ram_be_n := io.r_ram_be_n
    Serial_obj.io.r_serial := Mux((mem_addr_judgment === Serial_Addr &io.mem_read_en),true.B,false.B)
    Serial_obj.io.w_serial := Mux((mem_addr_judgment === Serial_Addr &io.mem_write_en),true.B,false.B)
    Serial_obj.io.w_serial_data :=  io.mem_write_Data
    Serial_obj.io.if_get_status := Mux(MemoryAccess_addr === "hBFD003FC".U(32.W),true.B,false.B)
    Serial_obj.io.serial_clock := io.serial_clock
    

}
