#include <stdio.h>
#include <string.h>

int combineNibbles(int leftNibble, int rightNibble);
int* extractNibbles(char cIn);

int main(int argc, const char* argv[]) {
	int encodingTable[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ',', '.', '-', '\n', 'S', 'E'};
	int cIn = 'd';
	int* temp;
	temp = malloc((2 * sizeof(int)));
	temp = extractNibbles(cIn);
	int l = cIn >> 4;
	int r = cIn % 16;
	int cOut = combineNibbles(l, r);
	printf("Character in: %c \t out: %c\n", cIn, cOut);
	return 0;
}

int combineNibbles(int leftNibble, int rightNibble) {
	printf("Left nibble: %d\nRight nibble: %d\n", leftNibble, rightNibble);
	leftNibble = leftNibble << 4;
	return leftNibble + rightNibble;
}
int* extractNibbles(char cIn) {
	int *temp = malloc((2 * sizeof(int)));
	*(temp) = cIn >> 4;
	*(temp+1) = cIn % 16;
	return temp;
}
