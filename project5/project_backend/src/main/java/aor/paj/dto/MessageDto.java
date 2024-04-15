package aor.paj.dto;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;


import java.time.LocalDateTime;
import java.util.Date;

@XmlRootElement
public class MessageDto {

    @XmlElement
    private long id;
    @XmlElement
    private String content;
    @XmlElement
    private User sender;
    @XmlElement
    private User receiver;

    private LocalDateTime timestamp;
    @XmlElement
    private boolean read;

    public MessageDto() {

        this.id = new Date().getTime();
        this.content = null;
        this.sender = null;
        this.receiver = null;
        this.timestamp = LocalDateTime.now();
        this.read = false;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
