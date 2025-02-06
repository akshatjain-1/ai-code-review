from fastapi import FastAPI
from pydantic import BaseModel
import ast

app = FastAPI(title="AI Code Review Service")

class CodeSnippet(BaseModel):
    code: str

@app.post("/analyze")
def analyze_code(snippet: CodeSnippet):
    """
    Analyze code for potential vulnerabilities and style issues.
    For demonstration, we use AST parsing to detect the use of `eval()`.
    In a real system, you might integrate a transformer-based model.
    """
    suggestions = []

    # --- Static Analysis using AST ---
    try:
        tree = ast.parse(snippet.code)
        for node in ast.walk(tree):
            if isinstance(node, ast.Call):
                # Example: flag use of eval()
                if hasattr(node.func, 'id') and node.func.id == 'eval':
                    suggestions.append("Avoid using eval() due to security risks.")
    except Exception as e:
        suggestions.append(f"Error parsing code: {str(e)}")

    # --- (Optional) AI/ML Analysis ---
    # Here you could load a transformer model (e.g., CodeBERT) and run inference.
    # For example:
    # from transformers import AutoTokenizer, AutoModelForSequenceClassification
    # tokenizer = AutoTokenizer.from_pretrained("microsoft/codebert-base")
    # model = AutoModelForSequenceClassification.from_pretrained("your-finetuned-model")
    # inputs = tokenizer(snippet.code, return_tensors="pt")
    # outputs = model(**inputs)
    # suggestions.append("Suggestion based on ML model output")

    if not suggestions:
        suggestions.append("No issues detected. Good job!")
    return {"suggestions": suggestions}
