import chisel3._
import chisel3.util._

class async_receiver extends  BlackBox with HasBlackBoxPath {
    val io = IO(new Bundle{
        val clk = Input(Clock())
        val RxD = Input(Bool())
        val RxD_data = Output(UInt(8.W))
        val RxD_data_ready = Output(Bool())
        val RxD_clear = Input(Bool())
    })
    addPath("./src/main/Memctl/async.v")
}