import { describe, it, expect, vi, beforeEach } from 'vitest'

// Mock @vitejs/plugin-vue before importing vite.config.js
vi.mock('@vitejs/plugin-vue', () => ({
  default: vi.fn(() => ({ name: 'vite:vue' })),
}))

// We capture the loadEnv mock so we can control its return value per test
const mockLoadEnv = vi.fn()

vi.mock('vite', () => ({
  // defineConfig just returns its argument so the default export of
  // vite.config.js becomes the raw factory function ({ mode }) => { ... }
  defineConfig: (fn) => fn,
  loadEnv: (...args) => mockLoadEnv(...args),
}))

// Import AFTER mocks are set up
const { default: configFactory } = await import('./vite.config.js')

describe('vite.config.js', () => {
  beforeEach(() => {
    vi.clearAllMocks()
  })

  // ── Error handling ────────────────────────────────────────────────────────

  it('throws when VITE_API_BASE_URL is not set', () => {
    mockLoadEnv.mockReturnValue({})

    expect(() => configFactory({ mode: 'development' })).toThrow(
      'Missing required env: VITE_API_BASE_URL'
    )
  })

  it('throws when VITE_API_BASE_URL is an empty string', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: '' })

    expect(() => configFactory({ mode: 'development' })).toThrow(
      'Missing required env: VITE_API_BASE_URL'
    )
  })

  // ── Proxy configuration ───────────────────────────────────────────────────

  it('sets proxy target to VITE_API_BASE_URL', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })

    expect(config.server.proxy['/api'].target).toBe('http://localhost:8000')
  })

  it('enables changeOrigin on the /api proxy', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })

    expect(config.server.proxy['/api'].changeOrigin).toBe(true)
  })

  it('proxy rewrite strips the /api prefix', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })
    const rewrite = config.server.proxy['/api'].rewrite

    expect(rewrite('/api/users')).toBe('/users')
    expect(rewrite('/api/boards/1')).toBe('/boards/1')
    expect(rewrite('/api')).toBe('')
  })

  // ── File-watch polling ────────────────────────────────────────────────────

  it('enables usePolling when VITE_USE_POLLING is "true"', () => {
    mockLoadEnv.mockReturnValue({
      VITE_API_BASE_URL: 'http://localhost:8000',
      VITE_USE_POLLING: 'true',
    })

    const config = configFactory({ mode: 'development' })

    expect(config.server.watch.usePolling).toBe(true)
  })

  it('disables usePolling when VITE_USE_POLLING is "false"', () => {
    mockLoadEnv.mockReturnValue({
      VITE_API_BASE_URL: 'http://localhost:8000',
      VITE_USE_POLLING: 'false',
    })

    const config = configFactory({ mode: 'development' })

    expect(config.server.watch.usePolling).toBe(false)
  })

  it('disables usePolling when VITE_USE_POLLING is not set', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })

    expect(config.server.watch.usePolling).toBe(false)
  })

  it('disables usePolling for non-"true" truthy-looking strings', () => {
    mockLoadEnv.mockReturnValue({
      VITE_API_BASE_URL: 'http://localhost:8000',
      VITE_USE_POLLING: '1',
    })

    const config = configFactory({ mode: 'development' })

    // Only the exact string "true" should enable polling
    expect(config.server.watch.usePolling).toBe(false)
  })

  // ── loadEnv invocation ────────────────────────────────────────────────────

  it('calls loadEnv with the provided mode', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://api.example.com' })

    configFactory({ mode: 'production' })

    expect(mockLoadEnv).toHaveBeenCalledWith('production', expect.any(String))
  })

  // ── Resolve aliases ───────────────────────────────────────────────────────

  it('defines the "@" path alias', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })

    expect(config.resolve.alias['@']).toBeDefined()
    expect(config.resolve.alias['@']).toContain('src')
  })

  // ── Plugins ───────────────────────────────────────────────────────────────

  it('includes the Vue plugin', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'http://localhost:8000' })

    const config = configFactory({ mode: 'development' })

    expect(config.plugins).toHaveLength(1)
    expect(config.plugins[0]).toMatchObject({ name: 'vite:vue' })
  })

  // ── Cross-env sanity ──────────────────────────────────────────────────────

  it('does not throw in production mode when VITE_API_BASE_URL is set', () => {
    mockLoadEnv.mockReturnValue({ VITE_API_BASE_URL: 'https://api.prod.example.com' })

    expect(() => configFactory({ mode: 'production' })).not.toThrow()
  })

  it('uses different API URLs per mode', () => {
    const devUrl = 'http://localhost:8000'
    const prodUrl = 'https://api.prod.example.com'

    mockLoadEnv
      .mockReturnValueOnce({ VITE_API_BASE_URL: devUrl })
      .mockReturnValueOnce({ VITE_API_BASE_URL: prodUrl })

    const devConfig = configFactory({ mode: 'development' })
    const prodConfig = configFactory({ mode: 'production' })

    expect(devConfig.server.proxy['/api'].target).toBe(devUrl)
    expect(prodConfig.server.proxy['/api'].target).toBe(prodUrl)
  })
})