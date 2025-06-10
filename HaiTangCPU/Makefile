BUILD_DIR = ./build
mywork_BUILD_DIR = ../thinpad_top.srcs/sources_1/new/
PRJ = playground

test:
	mill -i $(PRJ).test

verilog:
	$(call git_commit, "generate verilog")
	mill -i $(PRJ).runMain Elaborate --target-dir $(mywork_BUILD_DIR)
	@sed -i '$$d' $(mywork_BUILD_DIR)/HaiTangISA.sv
testVerilog:
	$(call git_commit, "generate verilog")
	mkdir -p $(BUILD_DIR)
	mill -i $(PRJ).runMain Elaborate --target-dir $(BUILD_DIR)
	@sed -i '$$d' $(BUILD_DIR)/HaiTangISA.sv

help:
	mill -i $(PRJ).runMain Elaborate --help

reformat:
	mill -i __.reformat

checkformat:
	mill -i __.checkFormat

clean:
	-rm -rf $(BUILD_DIR)
	-rm -rf $(mywork_BUILD_DIR)/HaiTangISA.v

.PHONY: test verilog help reformat checkformat clean

sim:
	$(call git_commit, "sim RTL") # DO NOT REMOVE THIS LINE!!!
	@echo "Write this Makefile by yourself."

-include ../Makefile
