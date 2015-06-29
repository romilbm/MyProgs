package hackerrank.algorithm.implementation;

import java.util.*;

public class FraudPrevention {

    public static class Address {
        private final String street;
        private final String city;
        private final String state;
        private final String zip;

        public Address(String street, String city, String state, String zip) {
            this.street = getFormattedStreet(street);
            this.city = city.toLowerCase();
            this.state = getFormattedState(state);
            this.zip = zip.split("\\-")[0];
        }
        
        

        @Override
        public boolean equals(Object o) {
            Address a;
            if(o instanceof Address) {
                a = (Address)o;
            } else {
                return false;
            }

            if(!a.getCity().equals(this.city)) {
                return false;
            }

            if(!a.getZip().equals(this.zip)) {
                return false;
            }

            if(!a.getStreet().equals(this.street)) {
                return false;
            }

            if(!a.getState().equals(this.state)) {
                return false;
            }

            return true;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZip() {
            return zip;
        }
    }

    public static class Email {

        private final String username;
        private final String domain;

        public Email(final String emailString) {
            String lowerCaseEmail = emailString.toLowerCase();
            String emailParts[] = lowerCaseEmail.split("@");
            domain = emailParts[1];
            String userParts[] = emailParts[0].split("\\.");
            String dotLessUsername = "";
            
            for(String up : userParts) {
                dotLessUsername += up;
            }

            username = dotLessUsername.split("\\+")[0];
            
        }

        @Override
        public boolean equals(Object o) {
            Email e;
            if(o instanceof Email) {
                e = (Email) o;
            } else {
                return false;
            }

            if(!e.getUsername().equals(username)) {
                return false;
            }

            if(!e.getDomain().equals(domain)) {
                return false;
            }

            return true;
        }

        public String getUsername() {
            return username;
        }

        public String getDomain() {
            return domain;
        }
    }

    public static class OrderDetails {
        private final Integer orderId;
        private final Integer dealId;
        private final Address address;
        private final Email email;
        private final String creditCardNumber;

        public OrderDetails(String inputParts[]) {
            this.orderId = Integer.parseInt(inputParts[0]);
            this.dealId = Integer.parseInt(inputParts[1]);
            this.email = new Email(inputParts[2]);
            this.address = new Address(inputParts[3], inputParts[4], inputParts[5], inputParts[6]);
            this.creditCardNumber = inputParts[7];
        }

        public Integer getDealId() {
            return dealId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public Address getAddress() {
            return address;
        }

        public Email getEmail() {
            return email;
        }

        public String getCreditCardNumber() {
            return creditCardNumber;
        }

        @Override
        public boolean equals(Object o) {
            OrderDetails od;
            if(o instanceof OrderDetails){
                od = (OrderDetails) o;
            } else {
                return false;
            }

            if(!od.getCreditCardNumber().equals(creditCardNumber)) {
                return false;
            }

            if(!od.getOrderId().equals(orderId)) {
                return false;
            }

            if(!od.getEmail().equals(email)) {
                return false;
            }

            if(!od.getAddress().equals(address)) {
                return false;
            }

            if(!od.getDealId().equals(dealId)) {
                return false;
            }
            return true;
        }
    }

    private static String getFormattedStreet(final String s) {
        String parts[] = s.toLowerCase().trim().split(" ");
        if(equalityMap.containsKey(parts[2].toLowerCase())) {
            parts[2] = equalityMap.get(parts[2].toLowerCase());
        }

        String street = "";
        for(String p : parts) {
            street += p.toLowerCase() + " ";
        }

        return street.trim();
    }

    private static String getFormattedState(final String s) {
        String state;
        if(equalityMap.containsKey(s.toLowerCase())) {
            state = equalityMap.get(s.toLowerCase());
        } else {
            state = s.toLowerCase();
        }

        return state;
    }

    private static void initialize() {
        equalityMap.put("st.", "street");
        equalityMap.put("rd.", "road");
        equalityMap.put("il", "illinois");
        equalityMap.put("ca", "california");
        equalityMap.put("ny", "new york");
    }

    private static void addToGood(OrderDetails currentOrderDetails) {
        Integer dealId = currentOrderDetails.getDealId();
        ArrayList<OrderDetails> currentList;
        if(dealIdOrderDetailsGoodMap.containsKey(dealId)) {
            currentList = (ArrayList<OrderDetails>) dealIdOrderDetailsGoodMap.get(dealId);
        } else {
            currentList = new ArrayList<>();
        }
        currentList.add(currentOrderDetails);
        dealIdOrderDetailsGoodMap.put(dealId, currentList);
    }

    private static void addToFraud(OrderDetails currentOrderDetails) {
        Integer dealId = currentOrderDetails.getDealId();
        ArrayList<OrderDetails> currentList;
        if(dealIdOrderDetailsFraudMap.containsKey(dealId)) {
            currentList = (ArrayList<OrderDetails>) dealIdOrderDetailsFraudMap.get(dealId);
        } else {
            currentList = new ArrayList<>();
        }
        currentList.add(currentOrderDetails);
        dealIdOrderDetailsFraudMap.put(dealId, currentList);
        fraudOrders.add(currentOrderDetails.getOrderId());
    }

    private static void processOrderDetails(OrderDetails currentOrderDetails) {
        Integer dealId = currentOrderDetails.getDealId();
        if(!dealIdOrderDetailsGoodMap.containsKey(dealId)
                && !dealIdOrderDetailsFraudMap.containsKey(dealId)) {
            addToGood(currentOrderDetails);
            return;
        }

        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        if(dealIdOrderDetailsGoodMap.containsKey(dealId)) {
            orderDetails.addAll(dealIdOrderDetailsGoodMap.get(dealId));
        }

        if(dealIdOrderDetailsFraudMap.containsKey(dealId)) {
            orderDetails.addAll(dealIdOrderDetailsFraudMap.get(dealId));
        }

        boolean isCurrentODFraud = false;
        for(OrderDetails od: orderDetails) {
            boolean isODFraud = false;
            if(!od.getCreditCardNumber().equals(currentOrderDetails.getCreditCardNumber())){
                if(od.getAddress().equals(currentOrderDetails.getAddress())) {
                    isCurrentODFraud = true;
                    isODFraud = true;
                } else if(od.getEmail().equals(currentOrderDetails.getEmail())) {
                    isCurrentODFraud = true;
                    isODFraud = true;
                }
            }

            if(isODFraud) {
                removeFromRecord(od);
                addToFraud(od);
            }
        }

        if(isCurrentODFraud) {
            addToFraud(currentOrderDetails);
        } else {
            addToGood(currentOrderDetails);
        }

    }

    private static void removeFromRecord(OrderDetails od) {
        Integer dealId = od.getDealId();
        if(dealIdOrderDetailsGoodMap.containsKey(dealId)) {
            ArrayList<OrderDetails> al = (ArrayList<OrderDetails>) dealIdOrderDetailsGoodMap.get(dealId);
            al.remove(od);
            if(al.size() > 0) {
                dealIdOrderDetailsGoodMap.put(dealId, al);
            } else {
                dealIdOrderDetailsGoodMap.remove(dealId);
            }
        }
    }

    public static Map<Integer, List<OrderDetails>> dealIdOrderDetailsGoodMap = new HashMap<>();
    public static Map<Integer, List<OrderDetails>> dealIdOrderDetailsFraudMap = new HashMap<>();
    public static Set<Integer> fraudOrders = new HashSet<>();
    public static Map<String, String> equalityMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        initialize();

        for(int i=0; i<n; i++){
            OrderDetails currentOrderDetails = new OrderDetails(in.nextLine().split(","));
            processOrderDetails(currentOrderDetails);
        }

        ArrayList<Integer> tempList = new ArrayList<>();
        tempList.addAll(fraudOrders);
        Collections.sort(tempList);

        for(int i=0; i<tempList.size(); i++) {
            System.out.print(tempList.get(i));
            if(i != (tempList.size() - 1)){
                System.out.print(",");
            }
        }
        System.out.println();
        System.out.println(tempList.size());
    }
}