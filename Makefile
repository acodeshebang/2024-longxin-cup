all:
	cd HaiTangCPU/ && make verilog
	cd ..
	git add .
	git commit -m "test"
	git push
