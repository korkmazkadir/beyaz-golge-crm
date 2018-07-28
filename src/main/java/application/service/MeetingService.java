package application.service;

import application.model.Meeting;
import application.model.Registration;
import application.repository.MeetingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingService {

    @Autowired
    MeetingRepository meetingRepository;

    public List<Meeting> getAllMeetings(Registration registration) {
        List<Meeting> meetings = meetingRepository.findByRegistrationOrderByDateDesc(registration);
        System.out.println("Meetings length : " + meetings.size());
        return meetings;
    }

    public Meeting saveMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

}
