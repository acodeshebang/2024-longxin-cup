import chisel3._
import chisel3.util._


class async_transmitter extends  BlackBox with HasBlackBoxPath {
    val io = IO(new Bundle{
        val clk = Input(Clock())
        val TxD_start = Input(Bool())
        val TxD_data = Input(UInt(8.W))
        val TxD = Output(Bool())
        val TxD_busy = Output(Bool())
    })
    
    addPath("./src/main/Memctl/async.v")
}