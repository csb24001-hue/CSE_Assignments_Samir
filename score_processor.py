class ScoreProcessor:

    def process_score_file(
        self,
        file_path: str
    ) -> int:

        try:

            with open(
                file_path,
                "r"
            ) as file:

                score = int(
                    file.read().strip()
                )

        except FileNotFoundError:

            print(
                "Error: File not found"
            )
            raise

        except ValueError:

            print(
                "Error: Invalid data format"
            )
            raise

        else:

            print(
                "Data processed successfully"
            )

            return score * 10

        finally:

            print(
                "File cleanup completed"
            )