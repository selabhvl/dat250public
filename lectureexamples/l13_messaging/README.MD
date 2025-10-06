# Notes

- Declaring queue is idempotent, i.e. queue is unchanged if it already exists.
  - However, this also means that it is not possible to redefine an existing queue (exception)
- When a queue is persistent, messages must be marked as persistent via `MessageProperties.*`
when using `basicPublish()`
- By default consumers do not acknowledge a consumed message, thus
they are explicitly acknowledged with `channel.basicAck(message.getEnvelope().getDeliveryTag(), false)`
- Queues are by default competing and messages get assigned in a round robbin fashion. To change this, 
an _exchange_ may be declared.
  - There are different exchange types `direct`, `topic`, `header`, `fanout`. The 
  get created via `channel.exchangeDeclare()`
  - The _producer_ now sends to the _exchange_ (queue name is kept empty)
  - Each _consumer_ defines an anonymous queue with `channel.queueDeclare().getQueue()` and binds
  it to the exchange `channel.queueBind(queueName, EXCHANGE_NAME, "")`. The last argument is the _binding key_
  which is important for `direct` exchanges.
  - `direct` exchanges route messages, where the exchange key exactly matches the binding name: `channel.basicPublish(EXCHANGE_NAME, BINDING_KEY, null, message.getBytes())`
    `channel.queueBind(queueName, EXCHANGE_NAME, BINDING_KEY);`
  - the `topic` exchange is quite similar, but here the binding key may have wildcards.
  The key is assumed to be a sequence of words separated by dots. The `*` may substitute one word,
  the hashtag `#` arbitrary many words
  - When wanting to perform RPC: the `new BasicProperties.Builder()` can be used to set a correlation id and a reply to channel.