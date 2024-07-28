package com.example.Controller;
import com.example.Api.Api;
import com.example.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private List<Event> events = new ArrayList<>();

    @GetMapping("/get")
    public List<Event> getAllEvents() {
        return events;
    }

    @PostMapping("/add")
    public Api createEvent(@RequestBody Event event) {
        events.add(event);
        return new Api("Event created");
    }

    @PutMapping("/update/{id}")
    public String updateEvent(@PathVariable int id, @RequestBody Event event) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == id) {
                events.set(i, event);
                return "Event updated";
            }
        }
        return "Event not found";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        events.removeIf(event -> event.getId() == id);
        return "Event deleted";
    }

    @PutMapping("/changeCapacity/{id}")
    public String changeCapacity(@PathVariable int id, @RequestParam int newCapacity) {
        for (Event event : events) {
            if (event.getId() == id) {
                event.setCapacity(newCapacity);
                return "Capacity changed for Event with ID " + id;
            }
        }
        return "Event not found";
    }

    @GetMapping("/search/{id}")
    public Event searchEventById(@PathVariable int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
}
}