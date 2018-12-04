	.global	v_bin
@	Subroutine v_bin will display a 32-bit register in binary digits
@		R0: contains the 32 bit value to be displayed in binary
@		R2: number of bits to be displayed (from right side of R0)
@		LR: contains the return address
@		All register contents will be preserved

v_bin:	push	{R0-R7}		@ save contents of registers R0 through R9
	push	{LR}		@ save the contents of the link register

	sub	R6,R2,#1	@ Number of bits to display (-1)
	cmp	R6,#31		@ Test error value entered (bit 31 is leftmost bit)
	movhi	R6,#0		@ if bad range default to displaying only 1 bit
	mov	R3,R0		@ R3 will hold a copy of input word to be displayed
	mov	R4,#1		@ used to mask off 1 bit at a time for display

	ldr 	R1,=lead	@ pointer to "0b"
	mov	R2,#2		@ number of characters to be displayed at a time
	mov	R0,#1		@ code for stdout
	mov	R7,#4		@ linux command code to write string
	svc	0

	mov	R0,R3		@ reset the value of R0 back to original

	ldr	R5,=dig		@ Pointer to the "01" string of ASCII characters
	mov	R2,#1		@ number of characters to be displayed at a time
	mov	R0,#1		@ code for stdout
	mov	R7,#4		@ linux command code to write string




@	Loop through single bits and output each to the stdout display

nxtbit:	and	R1,R4,R3,lsr R6	@ Select next 0 or 1 to be displayed
	cmp	R6,#0		@ check if R6 is on its last value
	movls	R9,#1		@ change R9 to 1 if R6 ison its last value
	cmp	R9,#0		@ see if R9 has a 1 in
	blls	chkbit		@ check whether the number to be displayed is a 0 or 1, only if R9 = 0
	add	R1,R5		@ set R1 pointing to "0" or "1" in memory
	svchi	0		@ linux service command code to write string, only if R9 != 0
	subs	R6,#1		@ decrement number of bits remaining to display
	bge	nxtbit		@ go display next bit until all 32 displayed

	pop	{LR}		@ Restore the value of the link register
	pop	{R0-R7}		@ Restore saved register contents

	bx	LR		@ return to the calling program

@	Checks if R1 has a 1 and sets the R9 to >0 if true

chkbit:	cmp	R1,#0		@ set the flags for whether there is a 0 or not
	movhi	R9,#1		@ change from 0 if R1 is 1, prevents the chkbit from running again
	bx	LR		@ Return to the calling program (nxtbit)

	.data
dig:	.ascii "01"	 	@ ascii string of binary digits 0 and 1
lead:	.ascii "0b"		@ ascii string for the preceding 0b
	.end
