from abc import ABC, abstractmethod


# -------------------------
# Payment Abstractions
# -------------------------

class PaymentMethod(ABC):

    @abstractmethod
    def pay(self, amount):
        pass


class CreditCardPayment(PaymentMethod):

    def pay(self, amount):
        print(f"Payment of ₹{amount} completed using Credit Card")


class UPIPayment(PaymentMethod):

    def pay(self, amount):
        print(f"Payment of ₹{amount} completed using UPI")


class WalletPayment(PaymentMethod):

    def pay(self, amount):
        print(f"Payment of ₹{amount} completed using Wallet")


# -------------------------
# Notification Abstractions
# -------------------------

class NotificationService(ABC):

    @abstractmethod
    def send(self, message):
        pass


class EmailNotification(NotificationService):

    def send(self, message):
        print("EMAIL:", message)


class SMSNotification(NotificationService):

    def send(self, message):
        print("SMS:", message)


class PushNotification(NotificationService):

    def send(self, message):
        print("PUSH:", message)


# -------------------------
# Storage Abstractions
# -------------------------

class Storage(ABC):

    @abstractmethod
    def save(self, order):
        pass


class DatabaseStorage(Storage):

    def save(self, order):
        print("Order saved in Database")


class FileStorage(Storage):

    def save(self, order):
        print("Order saved in File")


# -------------------------
# Order Types
# -------------------------

class Order:

    def __init__(self, order_id, amount):
        self.order_id = order_id
        self.amount = amount


class RegularOrder(Order):
    pass


class DiscountedOrder(Order):
    pass


class PriorityOrder(Order):
    pass


# -------------------------
# Order Service (DIP)
# -------------------------

class OrderService:

    def __init__(
        self,
        payment_method,
        notification_service,
        storage_service
    ):
        self.payment_method = payment_method
        self.notification_service = notification_service
        self.storage_service = storage_service

    def place_order(self, order):

        print("\nProcessing Order:",
              order.order_id)

        self.payment_method.pay(order.amount)

        self.notification_service.send(
            f"Order {order.order_id} placed successfully"
        )

        self.storage_service.save(order)

        print("Order Completed\n")


# -------------------------
# Main Program
# -------------------------

def main():

    order = PriorityOrder(
        order_id=101,
        amount=5000
    )

    service = OrderService(
        payment_method=UPIPayment(),
        notification_service=EmailNotification(),
        storage_service=DatabaseStorage()
    )

    service.place_order(order)


if __name__ == "__main__":
    main()