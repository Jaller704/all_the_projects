#
@	Subroutine v_hex will display a 32-bit register in hexidecimal digits
@		R0: Contains a number to be displayed in hexadecimal
@		R2: Number of nibbles to be displayed (from right side of R0)
@		    Note: If R2=0 or R2>8, leading 0's (on left) will not be displayed.
@		LR: Contains the return address
@		All register contents will be preserved

	.global v_hex
v_hex:	push	{R0-R7}			@ Save contents of registers R0 through R7
	mov	R3,R0			@ R3 will hold a copy of input word to be displayed
	mov	R4,#0b1111		@ Used to mask off 4 bits at a time for display
	mov	R6,R2,lsl#2		@ Load number of bits to display (4 bits per nibble)

@ 	Set up printing "0x" first

	ldr	R1,=lead		@ Pointer to the "0x"
	mov	R2,#2			@ Number of characters to be displayed at a time
	mov 	R0,#1			@ Code for stdout
	mov	R7,#4			@ Linux service command to write string

	svc	0			@ Linux service command code to write string

@ 	Set up registers for calling Linux to display 1 character on the display monitor

	ldr	R5,=dig			@ Pointer to the "012...EF" string of ASCII characters
	mov	R2,#1			@ Number of characters to be displayed at a time
	mov 	R0,#1			@ Code for stdout
	mov	R7,#4			@ Linux service command to write string

@	Determine number of bits to be output (R6 has that value if it's between 4 and 32)

	cmp	R6,#32			@ Test error value entered (there's only 32 bits in register)
	movhi	R6,#0			@ Default to omitting leading 0's if value > 32
	subs	R6,#4			@ Set R6 point to "right" side of first nibble to output
	bge	hex_bin			@ If proper range, then skip over finding first non-zero

	mov	R6,#28			@ Number of bits in register - number of bits per hex digit

@	Skip over leading 0's (on left)
@	Loop through groups of 4-bit nibbles and output each to stdout

hex_bin:ands	R1,R4,R3,LSR R6		@ Select next hex digit (0...F) to be displayed
	cmp	R8,#0			@ Check if R8 has any value other than 0
	cmpls 	R1,#0			@ If R8 = 0 check if R1 has any value other than 0 in it
	sublss	R6,#4			@ If R1 = 0 decrement number of bits remaining to display
	cmpls	R6,#0			@ Check if R6 has any remaining bits to display
	movls	R8,#1			@ If R6 = 0, set R8 to 1 to allow print

	cmp	R1,#0			@ Check if R1 has any value other than 0 to reset the flags
	movhi 	R8,#1			@ If R1 != 0, set R8 to 1
	cmp	R8,#0			@ Check if R8 has any value other than 0 to reset the flags 
	addhi	R1,R5			@ Set R1 pointing to "0", "1", ... or "F" in memory if R8 != 0
	svchi	0			@ Linux service command code to write string if R8 != 0
	subhis	R6,#4			@ Decrement number of bits remaining to display if R8 != 0
	bge	hex_bin			@ Go display next nibble until max bit-count reached

	pop	{R0-R7}			@ Restore saved register contents
	bx	LR			@ Return to the calling program

	.data
dig:	.ascii	"0123456789"		@ ASCII string of digits 0 through 9
	.ascii	"ABCDEF"		@ ASCII string of digits A through F
lead:	.ascii	"0x"			@ Leading 0x to signify hex value
	.end
