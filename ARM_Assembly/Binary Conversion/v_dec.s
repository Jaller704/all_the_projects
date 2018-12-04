	.global v_dec

@	Subroutine v_dec will display a 32-bit register in decimal digits
@		R0: contains a number to be displayed in decimal
@		    If negative (bit 31 = 1), then a minus sign will be output
@		LR: contains the return address
@		All register contents will be preserved

v_dec:	push	{R0-R11}	@ Save contents of registers R0 through R7

	mov	R3,R0		@ R3 will hold a copy of input word to be displayed
	mov	R8,R0		@ R8 will hold an unmodified copy of the input word to be displayed
	mov	R9,#1		@ R9 will hold the first decimal position for the comma to be placed
	mov	R2,#1		@ Number of characters to be displayed at a time
	mov	R0,#1		@ Code for stdout
	mov	R7,#4		@ Linux service command code to write string

@	If bit-31 is set, then register contains a negative number and "()" should be output

	cmp	R3,#0		@ Determine if brackets are needed
	bge	absval		@ If positive number, then just display it (absolute value)
@	ldr	R1,=msign	@ Address of minus sign in memory
	ldr	R1,=obrac	@ Address of open bracket sign in memory
	svc	0			@ Service call to print
	rsb	R3,R3,#0	@ Get absolute value (negative of negative) for display

absval:	cmp	R3,#10		@ Test whether only one's column is needed
	blt	onecol		@ Go output "final" column of display

@	Get highest power of ten this number will use (i.e., is it greater than 10?, 100?, ...)

	ldr	R6,=pow10+8	@ Point to 100's column of power of ten table
high10:	ldr	R5,[R6],#4	@ Load next higher power of ten table
	cmp	R3,R5		@ Test if we've reached the highest power of ten needed
	add	R9,#1		@ Holds the size of the number.
	bge	high10		@ Continue search for power of ten that is greater
	sub	R6,#8		@ We stepped two integers too far

@	Need to work out where the comma goes based on the size of the number e.g. size 4, comma
@	after the first decimal place, size 5, comma after the second decimal place

fstcom:	sub	R9,#3		@ Subtracting till we get a negative number
	cmp	R9,#0		@ Checking if we are negative yet
	bgt	fstcom		@ Keep looping till a negative is reached
	add	R9,#3		@ Change from a negative to a positve, gives us the decimal place for the comma

@	Loop through powers of 10 and output each to the stdout

nxtdec:	ldr	R1,=dig-1	@ Point to 1 byte before "0123456789" string
	ldr	R5,[R6],#-4	@ Load next power of 10 (move right 1 dec column)
	cmp	R11,R9		@ Check if R11 and R9 are equal as in have we reached the 1st, 2nd, or 3rd decimal place from the left
	moveq	R10,R1		@ Store a copy of R1's value
	ldreq	R1,=comma	@ Load the comma in to print
	svceq	0		@ Print the comma
	addeq	R9,#3		@ Adds 3 to R9 when R11 is equal to move three decimal places left
	moveq	R1,R10		@ Reset R1


@	Loop through the next base ten digit to be displayed (i.e., thousands, hundred, ...)

mod10:	add	R1,#1		@ Set R1 pointing to the next higher digit '0' through '9'
	subs	R3,R5		@ Do a count down to find the correct digit
	bge	mod10		@ Keep subtracting current decimal column value
	addlt	R3,R5		@ We counted one too many (went negative)
	svc	0		@ Write the next digit to display
	add	R11,#1		@ Increment R11 
	cmp	R5,#10		@ Test if we've gone all the way through the one's column
	bgt	nxtdec		@ If 1's column, go output rightmost digit and return

@	Finish decimal display by calculating the one's digit

onecol: ldr	R1,=dig		@ Pointer to "0123456789"
	add	R1,R3		@ Generate offset into "0123456789" for ones digit
	svc	0		@ Write out the final digit

	cmp	R8,#0		@ Determine if bracket sign is needed
	ldr	R1,=cbrac	@ Address of closed bracket sign in memory
	svcmi	0		@ Print closing bracket if negative

	pop	{R0-R11}	@ Restore saved register content
	bx	LR		@ Return to the calling program

pow10:	.word	1		@ 10^0
	.word	10		@ 10^1
	.word	100		@ 10^2
	.word	1000		@ 10^3
	.word	10000		@ 10^4
	.word	100000		@ 10^5
	.word	1000000		@ 10^6
	.word	10000000	@ 10^7
	.word	100000000	@ 10^8
	.word	1000000000	@ 10^9
	.word	0x7FFFFFFF	@ Largest integer in 31 bits (2,147,483,647)
dig:	.ascii	"0123456789"	@ Ascii string of digits 0 through 9
msign:	.ascii	"-"		@ Needed for negative decimal numbers
obrac:	.ascii	"("		@ The open bracket needed for negative decimal numbers
cbrac:	.ascii	")"		@ The closed bracket needed for negative decimal numbers
comma:	.ascii	","		@ The comma needed for numbers larger than 3 decimal places
	.end
