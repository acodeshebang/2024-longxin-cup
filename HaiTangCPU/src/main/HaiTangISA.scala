import chisel3._
import chisel3.util._

class HaiTangISA extends Module{
    val io = IO(
        new Bundle{
            ////BaseRAM信号
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

            //直连串口信号
            val txd = Output(Bool())
            val rxd = Input(Bool())

            //mem_clock
            val mem_clock = Input(Clock())
            val mem_rst = Input(Bool())

            //serial_clock
            val serial_clock = Input(Clock())
            val serial_rst = Input(Bool())
            
        }
    )
    val HaiTangCPU = Module(new HaiTangCPU())
    val Memctl = Module(new Memctl())
    Memctl.io.pc_addr:=HaiTangCPU.io.pc_addr
    HaiTangCPU.io.Instruction:=Memctl.io.Instruction
    HaiTangCPU.io.r_data  := Memctl.io.mem_read_Data
    Memctl.io.mem_read_addr := HaiTangCPU.io.r_addr
    Memctl.io.mem_write_Data := HaiTangCPU.io.w_data
    Memctl.io.mem_write_addr := HaiTangCPU.io.w_addr
    Memctl.io.mem_read_en := HaiTangCPU.io.r_en 
    Memctl.io.mem_write_en := HaiTangCPU.io.w_en  
    HaiTangCPU.io.if_pc_valid := Memctl.io.if_pc_valid
    Memctl.io.r_ram_be_n:=HaiTangCPU.io.r_ram_be_n

    io.txd:=Memctl.io.txd
    Memctl.io.rxd :=  io.rxd

    Memctl.io.base_ram_data_in := io.base_ram_data_in
    io.base_ram_data_out := Memctl.io.base_ram_data_out
    io.base_ram_addr := Memctl.io.base_ram_addr
    io.base_ram_be_n := Memctl.io.base_ram_be_n
    io.base_ram_ce_n := !Memctl.io.base_ram_ce_n
    io.base_ram_oe_n := !Memctl.io.base_ram_oe_n
    io.base_ram_we_n := !Memctl.io.base_ram_we_n

    Memctl.io.ext_ram_data_in := io.ext_ram_data_in
    io.ext_ram_data_out := Memctl.io.ext_ram_data_out
    io.ext_ram_addr := Memctl.io.ext_ram_addr
    io.ext_ram_be_n := Memctl.io.ext_ram_be_n
    io.ext_ram_ce_n := !Memctl.io.ext_ram_ce_n
    io.ext_ram_oe_n := !Memctl.io.ext_ram_oe_n
    io.ext_ram_we_n := !Memctl.io.ext_ram_we_n
    io.if_base_ram_in := Memctl.io.if_base_ram_in
    io.if_ext_ram_in := Memctl.io.if_ext_ram_in

    // Memctl.clock := io.mem_clock
    // Memctl.reset := io.mem_rst
    Memctl.io.serial_clock := io.serial_clock
    Memctl.io.serial_rst := io.serial_rst
}

