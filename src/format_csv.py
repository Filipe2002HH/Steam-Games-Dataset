
import pandas as pd
import re

def format_csv(path_csv, output_path):
    df = pd.read_csv(
        path_csv,
        index_col=False
    )

    cols = ['AppID', 'Name', 'Release date', 'Full audio languages', 'Price', 'Metacritic score', 'Score rank']

    filtred_df = df[cols]
    filtred_df = filtred_df.rename(columns={"Full audio languages": "Supported languages", "Metacritic score": "Linux",  'Score rank': 'Achievements' })

    filtred_df['Release date'] = filtred_df['Release date'].str.replace(',', '')
    filtred_df['Supported languages'] = filtred_df['Supported languages'].str.replace(',', '')
    filtred_df['Name'] = filtred_df['Name'].str.replace(',', '')

    filtred_df.to_csv(output_path, quotechar='"', index=False, sep=",")

    return filtred_df

format_csv(
    "",
    ""
)