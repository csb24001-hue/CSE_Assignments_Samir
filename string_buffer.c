#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *data;
    size_t length;
    size_t capacity;
} StringBuffer;

StringBuffer* sb_init(size_t initial_capacity) {
    StringBuffer *sb = malloc(sizeof(StringBuffer));
    if (!sb) return NULL;

    sb->data = malloc(initial_capacity);
    if (!sb->data) {
        free(sb);
        return NULL;
    }

    sb->length = 0;
    sb->capacity = initial_capacity;
    sb->data[0] = '\0';

    return sb;
}

void sb_append(StringBuffer *sb, const char *str) {
    size_t new_len = sb->length + strlen(str);

    while (new_len + 1 > sb->capacity) {
        size_t new_cap = sb->capacity * 2;

        char *temp = realloc(sb->data, new_cap);

        if (!temp) {
            printf("Realloc failed\n");
            return;
        }

        sb->data = temp;
        sb->capacity = new_cap;
    }

    strcat(sb->data, str);
    sb->length = new_len;
}

void sb_free(StringBuffer *sb) {
    free(sb->data);
    free(sb);
}

int main() {
    StringBuffer *sb = sb_init(10);

    sb_append(sb, "Hello");
    sb_append(sb, " World");
    sb_append(sb, " Dynamic String Buffer Example");

    printf("%s\n", sb->data);
    printf("Length=%zu Capacity=%zu\n",
           sb->length,
           sb->capacity);

    sb_free(sb);

    return 0;
}