package com.reviewpilot.bank.ifsc;

import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ifsc")
public class IfscController {

    // 4 letters (bank), a literal 0, then 6 alphanumerics (branch)
    private static final Pattern IFSC_FORMAT = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    private static final Map<String, IfscResponse> BRANCHES = Map.of(
            "HDFC0000001", new IfscResponse("HDFC0000001", "HDFC Bank", "Mumbai Main", "Mumbai"),
            "SBIN0000691", new IfscResponse("SBIN0000691", "State Bank of India", "Bengaluru Main", "Bengaluru"),
            "ICIC0000007", new IfscResponse("ICIC0000007", "ICICI Bank", "Delhi Connaught Place", "Delhi"));

    public record IfscResponse(String ifsc, String bank, String branch, String city) {
    }

    @GetMapping("/{code}")
    public IfscResponse getByIfsc(@PathVariable String code) {
        String ifsc = code.toUpperCase();
        if (!IFSC_FORMAT.matcher(ifsc).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid IFSC format");
        }
        IfscResponse response = BRANCHES.get(ifsc);
        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IFSC not found");
        }
        return response;
    }
}
