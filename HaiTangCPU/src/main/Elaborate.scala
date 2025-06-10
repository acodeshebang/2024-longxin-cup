object Elaborate extends App {
  val firtoolOptions = Array("--lowering-options=" + List(
    // make yosys happy
    // see https://github.com/llvm/circt/blob/main/docs/VerilogGeneration.md
    "disallowLocalVariables",
    "disallowPackedArrays",
    "locationInfoStyle=wrapInAtSquareBracket"
  ).reduce(_ + "," + _))
  circt.stage.ChiselStage.emitSystemVerilogFile(new HaiTangISA(), args, firtoolOptions)
}

// import circt.stage._
// object Elaborate extends App {
//   def top = new HaiTangISA()
//   val generator = Seq(chisel3.stage.ChiselGeneratorAnnotation(() => top))
//   (new ChiselStage).execute(args, generator :+ CIRCTTargetAnnotation(CIRCTTarget.Verilog))
// }