package encryptdecrypt;

interface Algorithm {
    StringBuilder decrypt(String secret, int key);
    StringBuilder encrypt(String message, int key);
}

class ShiftAlgorithm implements Algorithm {
    @Override
    public StringBuilder decrypt(String secret, int key) {
        StringBuilder message = new StringBuilder(secret.length());
        for (int i = 0; i < secret.length(); i++) {
            if (Character.isAlphabetic(secret.charAt(i))) {
                message.append(rotate(secret.charAt(i), -key));
            } else {
                message.append(secret.charAt(i));
            }
        }

        return message;
    }
    @Override
    public StringBuilder encrypt(String message, int key) {
        StringBuilder secret = new StringBuilder(message.length());
        for (int i = 0; i < message.length(); i++) {
            if (Character.isAlphabetic(message.charAt(i))) {
                secret.append(rotate(message.charAt(i), key));
            } else {
                secret.append(message.charAt(i));
            }
        }
        return secret;
    }

    private char rotate(char c, int key) {
        int value = c + key;
        char point = 'a';
        if (Character.isUpperCase(c)) {
            point = 'A';
        }
        value -= point;
        value %= 26;
        value += 26;
        value %= 26;
        value += point;
        return (char) value;
    }
}

class UnicodeAlgorithm implements Algorithm {
    @Override
    public StringBuilder decrypt(String secret, int key) {
        StringBuilder message = new StringBuilder(secret.length());

        for (int i = 0; i < secret.length(); i++) {
            int dec = secret.charAt(i) - key;
            dec = dec < 0 ? 127 + dec: dec;
            message.append((char) dec);
        }
        return message;
    }
    @Override
    public  StringBuilder encrypt(String message, int key) {
        StringBuilder secret = new StringBuilder(message.length());

        for (int i = 0; i < message.length(); i++) {
            int sec = message.charAt(i) + key;
            sec = sec % 127;
            secret.append((char) sec);
        }
        return secret;
    }
}

class AlgoFactory {
    public static Algorithm selectAlgo(String name) {
        Algorithm newAlgo = null;
        switch (name) {
            case "shift":
                newAlgo = new ShiftAlgorithm();
                break;
            case "unicode":
                newAlgo = new UnicodeAlgorithm();
                break;
        }
        return newAlgo;
    }
}