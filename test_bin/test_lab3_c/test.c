#include <stdio.h>

void crn(int pad[], unsigned int a, unsigned int b, unsigned int n)
{
    unsigned int k;
    for (k = 0; k != 0x80000; k++)
        pad[k] = k;
    for (k = 0; k != n; k++)
    {
        unsigned int t, addr1, addr2;
        addr1 = a & 0x7FFFF;
        t = (a >> 1) ^ (pad[addr1] << 1); // Replace the AES step
        pad[addr1] = t ^ b;
	printf("%x\n",addr1);
        addr2 = t & 0x7FFFF;
        b = t;
        t = pad[addr2];

        a += b * t;
        pad[addr2] = a;
	printf("%x\n",addr2);
        a ^= t;
    }
}

int main()
{
    int pad[0x100000];
    crn(pad,0xdeadbeef,0xfaceb00c,0x1 );
    printf("%x-%x",pad[0x5beef],pad[0x5a2a9]);
    for(int i=0;i<0x80000;i++){
	    //printf("%x\n",pad[i]);
}}
