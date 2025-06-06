
    let stompClient = null;

    function connect() {
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);

            // 👇 Samo jednom se pretplati
            stompClient.subscribe('/topic/notifications', function (notification) {
                const data = JSON.parse(notification.body);
                
                if (data.type === "contact") {
                    showToast("📩 New contact message: " + data.message, "contact");
                } else if (data.type === "booking") {
                    showToast("📅 New booking: " + data.message, "booking");
                }
            });

        }, function (error) {
            console.error('WebSocket connection error: ' + error);
        });
    }

    function showToast(message, type) {
        const toast = document.getElementById("toast");
        const toastMsg = document.getElementById("toast-message");
        const toastBtn = document.getElementById("toast-btn");

        toastMsg.textContent = message;
        toast.style.display = "block";

        toastBtn.onclick = function () {
            if (type === "booking") {
                window.location.href = "/admin/readAllBookings";
            } else if (type === "contact") {
                window.location.href = "/admin/readAllContacts";
            }
        };

        setTimeout(() => {
            toast.style.display = "none";
        }, 15000);
    }

    connect();