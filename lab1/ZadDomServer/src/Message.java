public class Message {
    private final int authorId;
    private final String type; // "text", "ASCIIart"
    private final boolean isTCP;
    private final byte[] bytes;

    public Message(int authorId, String type, boolean isTCP, byte[] bytes, int offset, int length){
        this.authorId = authorId;
        this.type = type;
        this.isTCP = isTCP;
        this.bytes = new byte[length];
        System.arraycopy(bytes, offset, this.bytes, 0, length);
    }

    public Message(int authorId, String type, boolean isTCP, byte[] bytes){
        this.authorId = authorId;
        this.type = type;
        this.isTCP = isTCP;
        this.bytes = new byte[bytes.length];
        System.arraycopy(bytes, 0, this.bytes, 0, bytes.length);
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getType() {
        return type;
    }

    public boolean isTCP() {
        return isTCP;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
