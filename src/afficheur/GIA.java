package afficheur;

public interface GIA {
	
	// ********************************************* //
	// *** ** * GENERAL AFFICHAGE INTERFACE * ** *** // 
	// ********************************************* //
	
	// Interface pour affichage
	
	public int SIZE_SQUARE = 16;
	public int MULTIPLICATOR = 3;
	public int SIZE = SIZE_SQUARE*MULTIPLICATOR;
	// Let's have 21 squares on axeX and 13 squares on axeY. 
	
	public int CX = 10; // The windows have a size of (2CX+1) x (2CY+1) cases
	public int CY = 6;
	public int OFFSET = 0;
	
	public int totalSizeX = CX * SIZE;
	public int totalSizeY = CY * SIZE;
	
	
}