package org.example.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Command {
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F');

    private final char value;


    public static Command from(char value){
        return switch (Character.toUpperCase(value)){
            case 'L' -> LEFT;
            case 'R' -> RIGHT;
            case 'F' -> FORWARD;
            default -> throw new IllegalArgumentException("Invalid command: "+value);
        };
    }
}
