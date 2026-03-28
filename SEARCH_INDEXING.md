# Search And AI Indexing Notes

This repository now includes the following discovery files:

- `/llms.txt`
- `/llms-ctx-full.txt`
- `/llms-full.txt`
- `/robots.txt`
- `/sitemap.xml`

These files are useful only if they are published at the root of the real public site.

## Important Deployment Note

This repository is `https://github.com/iohao/ionet-examples`, but the URLs referenced by the existing sitemap and robots files point to:

- `https://iohao.github.io/ionet/`

That means the files in this repository help only after they are copied or synchronized to the root of the deployed `iohao.github.io/ionet` site.

## What Actually Helps AI Discovery

- `robots.txt`
  Controls crawler access. This matters for search bots and AI crawlers that respect robots rules.

- `sitemap.xml`
  Helps crawlers discover important URLs more efficiently. It improves discovery, but does not guarantee indexing.

- `llms.txt`
  An emerging, community proposal for giving LLMs a curated overview of a site and its most useful links.

- `llms-ctx-full.txt`
  Closer to the naming used by the `llms.txt` proposal ecosystem for a fuller expanded context variant.

- `llms-full.txt`
  A compatibility alias in this repository for teams or tools that look for a simpler "full" file name.

- page-level metadata
  Canonical links, robots meta tags, and structured data can improve discoverability and interpretation on the real site pages.

## What Site Verification Does

Site verification files and meta tags do not directly cause AI indexing.

They are mainly used to prove ownership in tools such as search consoles so you can:

- submit sitemaps
- inspect crawl and indexing issues
- request re-crawls
- monitor search performance

## Files Added For Templates

- `site-verification/homepage-head.example.html`
  Copy the relevant meta tags into the `<head>` of the deployed homepage.

- `site-verification/google-site-verification.example.html`
  Example only. Replace both the file name and file content with the exact file downloaded from Google Search Console if you use file-based verification.

## Minimal Deployment Checklist

1. Publish `/robots.txt`, `/sitemap.xml`, `/llms.txt`, `/llms-ctx-full.txt`, and `/llms-full.txt` on the real site root.
2. If using Google Search Console HTML verification, upload the exact file Google gives you to the real site root.
3. If using meta-tag verification, paste the real verification tags into the deployed homepage `<head>`.
4. Keep sitemap URLs aligned with the actual canonical host.
5. After deployment, verify these URLs are publicly reachable without login.

## Recommended Next Step

If you want this to become truly effective, the next step is not editing this repository again. The next step is placing the same files on the source repository or publishing pipeline that actually builds `https://iohao.github.io/ionet/`.
