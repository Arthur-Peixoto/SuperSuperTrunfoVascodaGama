package Uno;

public class UnoCard {
	enum Color{
		Rouge, Bleu, Vert, Jaune, Colore;
		
		private static final Color[] colors = Color.values();
		private static Color getColors(int i) {
			return Color.colors[i];
		}
		
		
	}
	enum Value{
		Zero, Un, Deux, Trois, Quatre, Cinq, Six, Sept, Huit, Neuf, Dix, DessinerDeux, Inverse, Libre, Libre_Quatre, Sauter;
		
		private static final Value[] values = Value.values();
		public static Value getValue(int i) {
			return Value.values[i];
		}
	}
		private final Color color;
		
		public Color getColor() {
			return color;
		}

		public Value getValue() {
			return value;
		}

		private final Value value;
		
		public UnoCard(final Color color, final Value value){
			this.color = color;
			this.value = value;
		}
	
		public String toString()
		{
			return color + " " + value;
		} 
		
}
