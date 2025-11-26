import { useState } from 'react'
import './App.css'
import { createORPCClient } from '@orpc/client'
import { OpenAPILink } from '@orpc/openapi-client/fetch'
import {contract, type CreateUserRequest, CreateUserRequestSchema, type UserResponse} from './gen/contract'
import type { ContractRouterClient } from '@orpc/contract'

// 1. Link Setup
const link = new OpenAPILink(contract, {
    url: 'http://localhost:8080',
    headers: () => ({
        'Content-Type': 'application/json',
    }),
    fetch: (input, init) => {
        return globalThis.fetch(input, { ...init })
    }
})

const orpc = createORPCClient<ContractRouterClient<typeof contract>>(link)

function App() {
    const [resultId, setResultId] = useState<string | null>(null)
    const [error, setError] = useState<string | null>(null)

    const handleSubmit = async () => {
        setError(null)
        setResultId(null)

        const payload: CreateUserRequest = {
            username: "BobTheBuilder",
            email: "bob@construct.com",
            age: 25
        }

        // Frontend Validierung
        const validation = CreateUserRequestSchema.safeParse(payload)

        if (!validation.success) {
            const errorMsg = validation.error.issues.map(e => e.message).join(", ")
            setError("Frontend Validation: " + errorMsg)
            return
        }

        try {
            const response: UserResponse = await orpc.create(payload)

            console.log("Backend Response:", response)
            setResultId(response.id || "ID Missing")
        } catch (e: any) {
            console.error("API Error:", e)
            setError("Backend Error: " + (e.message || "Unknown"))
        }
    }

    return (
        <div className="card">
            <h2>React + Spring over oRPC</h2>
            <button onClick={handleSubmit}>Create User</button>

            {error && <p style={{color: 'red'}}>{error}</p>}
            {resultId && <p style={{color: 'green'}}>User ID: {resultId}</p>}
        </div>
    )
}

export default App
