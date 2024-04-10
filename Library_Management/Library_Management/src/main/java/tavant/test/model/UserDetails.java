package tavant.test.model;

    // Class representing user details
    public class UserDetails {
        private String password;
        private String role;

        public UserDetails(String password, String role) {
            this.password = password;
            this.role = role;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }


