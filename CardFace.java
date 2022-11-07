public enum CardFace{
    Hearts(1),
    Clubs(2),
    Diamonds(3),
    Spades(4);

    int faceValue;
    CardFace(int faceValue){
        this.faceValue = faceValue;
    }
    int getfaceValue(){
        return faceValue;
}