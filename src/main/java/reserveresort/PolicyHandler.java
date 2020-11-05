package reserveresort;

import reserveresort.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    @Autowired
    ConfirmRepository confirmRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReserved_WaitReservation(@Payload Reserved reserved){

        if(reserved.isMe()){
            Confirm confirm = new Confirm();
            confirm.setReserveId(reserved.getResortId());
            confirm.setResortId(reserved.getResortId());
            confirm.setRoomId(reserved.getRoomId());
            confirm.setPeopleCount(reserved.getPeopleCount());
            confirm.setStatus("WaitConfirm");
            System.out.println("##### listener WaitReservation : " + reserved.toJson());
        }
    }

}
