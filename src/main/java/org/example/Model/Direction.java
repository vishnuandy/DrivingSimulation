    package org.example.Model;

    import lombok.Getter;
    import lombok.RequiredArgsConstructor;

    @Getter
    @RequiredArgsConstructor
    public enum Direction {
        NORTH('N'),
        EAST('E'),
        SOUTH('S'),
        WEST('W');
        private final char value;

        public Direction turnLeft(){
            return switch (this){
                case NORTH -> WEST;
                case WEST -> SOUTH;
                case SOUTH -> EAST;
                case EAST -> NORTH;
            };
        }
        public Direction turnRight(){
            return switch (this){
                case NORTH -> EAST;
                case WEST -> NORTH;
                case SOUTH -> WEST;
                case EAST -> SOUTH;
            };
        }
        public static Direction from(char value){
            return switch (Character.toUpperCase(value)){
                case 'N' -> NORTH;
                case 'S' -> SOUTH;
                case 'W' -> WEST;
                case 'E' -> EAST;
                default -> throw new IllegalArgumentException("Invalid direction: "+value);
            };
        }
    }
