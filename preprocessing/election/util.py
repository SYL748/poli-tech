import pandas as pd

def calculate_percentage(df: pd.DataFrame, numerator_col: str, denominator_col: str, clip_at_100: bool = False) -> pd.Series:
    result = (df[numerator_col] / df[denominator_col] * 100).fillna(0).round(2)
    if clip_at_100:
        result = result.clip(upper=100.00)
    return result
