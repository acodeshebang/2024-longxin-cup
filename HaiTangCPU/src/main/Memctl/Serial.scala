import chisel3._
import chisel3.util._

class Serial extends Module{
    val io = IO(
        new Bundle{
            val RxD = Input(Bool())
            val TxD = Output(Bool())

            val if_get_status = Input(Bool())
            val r_ram_be_n = Input(UInt(4.W))
            val Serial_read_Data = Output(UInt(32.W))
            val w_serial_data = Input(UInt(32.W))
            val r_serial = Input(Bool())
            val w_serial = Input(Bool())
            val serial_clock = Input(Clock())

            val serial_pc_stop = Output(Bool())

            //debug
            val leds = Output(UInt(16.W))
            
        }
    )   
    val async_receiver_obj = Module(new async_receiver())
    val async_transmitter_obj = Module(new async_transmitter())
    async_receiver_obj.io.clk := clock//io.serial_clock
    async_transmitter_obj.io.clk := clock//io.serial_clock
    async_receiver_obj.io.RxD := io.RxD
    io.TxD := async_transmitter_obj.io.TxD
    var Serial_read_Data_reg = RegInit(0.S(32.W))
    val s_idle::waitForRxD_data_ready::get_data::success::Nil = Enum(4)
    val RXD_status = RegInit(s_idle)
    RXD_status := MuxLookup(RXD_status,s_idle)(
        List(
            s_idle -> Mux(io.r_serial & !io.if_get_status,waitForRxD_data_ready,s_idle),
            waitForRxD_data_ready -> Mux(async_receiver_obj.io.RxD_data_ready,get_data,s_idle),
            get_data -> success,
            success->s_idle
        )
    )
    async_receiver_obj.io.RxD_clear := Mux(RXD_status===success | reset.asBool ,true.B,false.B)
    Serial_read_Data_reg := Mux(RXD_status===get_data,Cat(async_receiver_obj.io.RxD_data).asSInt,Serial_read_Data_reg)
    io.Serial_read_Data := Mux(io.if_get_status,Cat(0.U(32.W),async_receiver_obj.io.RxD_data_ready,!async_transmitter_obj.io.TxD_busy).asUInt,Serial_read_Data_reg.asUInt)//Cat(0.U(1.W),0.U(29.W),0.U(1.W),!async_transmitter_obj.io.TxD_busy).asUInt//
    // io.serial_pc_stop := Mux()false.B//Mux(io.r_serial& !io.if_get_status,Mux(RXD_status===success,false.B,true.B),false.B)

    val txd_s_idle :: waitForTxD_no_busy ::transmit_data:: txd_success::Nil = Enum(4)
    val TXD_status = RegInit(txd_s_idle)
    TXD_status := MuxLookup(TXD_status,txd_s_idle)(
        List(
            txd_s_idle -> Mux(io.w_serial,waitForTxD_no_busy,txd_s_idle),
            waitForTxD_no_busy -> Mux(!async_transmitter_obj.io.TxD_busy,transmit_data,waitForTxD_no_busy),
            transmit_data -> txd_success,
            txd_success->txd_s_idle
        )
    )
    val TxD_data_reg = Reg(UInt(8.W))
    io.leds := Serial_read_Data_reg(15,0)//Cat(0.U(16.W),async_receiver_obj.io.RxD_data)//
    //val TxD_start_reg = RegInit(true.B)
    
    //TxD_start_reg:= Mux(TXD_status===transmit_data,true.B,false.B)
    async_transmitter_obj.io.TxD_start  := Mux(TXD_status===transmit_data,true.B,false.B)
    TxD_data_reg := Mux(io.w_serial,io.w_serial_data(7,0),TxD_data_reg)
    async_transmitter_obj.io.TxD_data := TxD_data_reg//io.w_serial_data(7,0)//TxD_data_reg//97.U(8.W)//

    io.serial_pc_stop := false.B//Mux(TXD_status=/= txd_s_idle,true.B,false.B)
    
    
}

// input wire TxD_start,
// 	input wire [7:0] TxD_data,
// 	output wire TxD_busy

//   val io = IO(new Bundle{
//         val clk = Input(Clock())
//         val RxD = Input(Bool())
//         val RxD_data = Output(UInt(8.W))
//         val RxD_data_ready = Output(Bool())
//         val RxD_clear = Output(Bool())
//     })

