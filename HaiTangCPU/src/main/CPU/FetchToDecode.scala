import chisel3._
import chisel3.util._

class FetchToDecode extends Module{
    val io = IO(
        new Bundle{
            val in = Flipped(Decoupled(new FetchToDecode_interface()))
            val out = Decoupled(new FetchToDecode_interface())
            val if_var_pc_Conflict = Input(Bool())
        }
    )
    val FetchToDecode_valid = RegInit(false.B)
    val FetchToDecode_go_in = Wire(Bool())
    



    FetchToDecode_valid := Mux(FetchToDecode_go_in,io.in.valid & io.if_var_pc_Conflict ,FetchToDecode_valid)
    
    FetchToDecode_go_in :=    (io.out.ready | !FetchToDecode_valid)//& !if_have_data_conflict

    io.in.ready := FetchToDecode_go_in
    io.out.valid := FetchToDecode_valid 


    val FetchToDecode_reg_init = Wire(Flipped(new FetchToDecode_interface()))
    FetchToDecode_reg_init.Instruction := 0.U
    FetchToDecode_reg_init.now_pc := 0.U
    val FetchToDecode_reg = RegEnable(io.in.bits,FetchToDecode_reg_init,FetchToDecode_go_in)
    io.out.bits := Mux(FetchToDecode_valid,FetchToDecode_reg,FetchToDecode_reg_init)
    


}